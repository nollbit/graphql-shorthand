package se.mjones.graphql.shorthand

import java.io.InputStream

import org.antlr.v4.runtime._
import se.mjones.graphql.shorthand.parser.{GraphQLShorthandParser, GraphQLShorthandLexer}
import scala.collection.JavaConverters._
import java.util.{List => JList}

import scala.collection.mutable

case class SyntaxError(line: Int, charPositionInLine: Int, msg: String)

trait ShorthandResult
case class UnresolvedError(line: Int, charPositionInLine: Int, msg: String) extends ShorthandResult
case class SyntaxErrors(errors: Seq[SyntaxError]) extends ShorthandResult
case class Schema(definitions: Seq[Definition]) extends ShorthandResult

trait Definition { val name: String }
case class Enum(name: String, values: Seq[String]) extends Definition
case class Interface(name: String, fields: Seq[Field]) extends Definition
case class Type(name: String, fields: Seq[Field], interface: Option[Interface] = None) extends Definition
case class Union(name: String, types: Seq[Definition]) extends Definition

trait GraphQLType
object GraphQL {
  case class Boolean() extends GraphQLType
  case class Int() extends GraphQLType
  case class String() extends GraphQLType
  case class DefinedType(definition: Definition) extends GraphQLType
  case class List(inner: GraphQLType) extends GraphQLType
}

case class Parameter(name: String, paramType: GraphQLType, notNull: Boolean = false)
case class Field(name: String, fieldType: GraphQLType, parameters: Seq[Parameter] = Seq.empty, notNull: Boolean = false)


class SyntaxErrorException(line: Int, charPositionInLine: Int, msg: String) extends Exception
class SyntaxErrorListener extends BaseErrorListener {
  val syntaxErrors = mutable.MutableList[SyntaxError]()

  override def syntaxError(recognizer: Recognizer[_, _],
                           offendingSymbol: scala.Any,
                           line: Int, charPositionInLine: Int,
                           msg: String, e: RecognitionException) = {
    syntaxErrors += SyntaxError(line, charPositionInLine, msg)
  }
}

case class UnresolvedException(line: Int, charPositionInLine: Int, msg: String) extends Exception(msg)

class Parser {

  def parseFromContexts(defs: Seq[GraphQLShorthandParser.DefinitionContext]) = {
    val enums = defs.filter(_.enumDefinition() != null).map { e =>
      val names = e.enumDefinition().NAME().asScala.map(_.getSymbol.getText)
      Enum(names.head, names.tail)
    }.map(e => (e.name, e)).toMap

    val rawInterfaces = defs.filter(_.interfaceDefinition() != null).map { i =>
      val d = i.interfaceDefinition()
      (d.NAME().getSymbol.getText, d)
    }.toMap
    val rawUnions = defs.filter(_.unionDefinition() != null).map { i =>
      val d = i.unionDefinition()
      (d.NAME(0).getSymbol.getText, d)
    }.toMap
    val rawTypes = defs.filter(_.typeDefinition() != null).map { t =>
      val d = t.typeDefinition()
      (d.NAME(0).getSymbol.getText, d)
    }.toMap

    def unresolved[T <: ParserRuleContext](ctx: T, msg: String) = {
      new UnresolvedException(ctx.getStart.getLine, ctx.getStart.getCharPositionInLine, msg)
    }

    def getEnum(name: String) = enums.get(name)

    def getGraphQLType(t: GraphQLShorthandParser.TypeContext): GraphQLType = {
      if (t.BOOLEAN() != null) {
        GraphQL.Boolean()
      } else if (t.INT() != null) {
        GraphQL.Int()
      } else if (t.STRING() != null) {
        GraphQL.String()
      } else {
        val named = t.NAME().getSymbol.getText
        val d = getDefinition(named)
        GraphQL.DefinedType(d)
      }
    }

    def getFieldType(fieldType: GraphQLShorthandParser.FieldTypeContext) = {
      val notNull = fieldType.nonNullType() != null
      val t = if (fieldType.listType() != null) {
        val inner = getGraphQLType(fieldType.listType().`type`())
        GraphQL.List(inner)
      } else {
        getGraphQLType(fieldType.`type`())
      }
      (notNull, t)
    }

    def getParameters(p:  GraphQLShorthandParser.ParametersContext) = {
      Option(p).map { p => p.parameter().asScala.map { pCtx =>
        val name = pCtx.NAME().getSymbol.getText
        val (notNull, t) = getFieldType(pCtx.fieldType())
        Parameter(name, t, notNull)
      }}.getOrElse(Seq.empty)
    }

    def getFields(fields: JList[GraphQLShorthandParser.FieldContext]) = {
      fields.asScala.map { fieldCtx =>
        val name = fieldCtx.NAME().getSymbol.getText
        val (notNull, t) = getFieldType(fieldCtx.fieldType())
        val params = getParameters(fieldCtx.parameters())

        Field(name, t, params, notNull)
      }
    }

    def getInterface(name: String) = {
      rawInterfaces.get(name).map { rawInterface =>
        val fields = getFields(rawInterface.field())
        Interface(name, fields)
      }
    }

    def getType(name: String) = {
      rawTypes.get(name).map { typeCtx =>
        val (name, interfaceName) = {
          val names = typeCtx.NAME().asScala.map(_.getSymbol.getText)
          (names.head, names.tail.headOption)
        }
        val interface = interfaceName.map { interfaceName =>
          getInterface(interfaceName).getOrElse { throw unresolved(typeCtx, s"No such interface $interfaceName")}
        }

        val fields = getFields(typeCtx.field())

        Type(name, fields, interface = interface)
      }
    }

    def getUnion(name: String) = {
      rawUnions.get(name).map { unionCtx =>
        val names = unionCtx.NAME().asScala.map(_.getSymbol.getText)
        Union(names.head, names.tail.map(getInterfaceOrType(unionCtx, _)))
      }
    }

    def getInterfaceOrType[T <: ParserRuleContext](ctx: T, name: String) = {
      getType(name) orElse getInterface(name) getOrElse {
        throw unresolved(ctx, s"No defined type named $name")
      }
    }

    def getDefinition(name: String) = {
      getUnion(name) orElse getType(name) orElse getInterface(name) orElse getEnum(name) getOrElse {
        throw new UnresolvedException(-1, -1, s"No defined type named $name")
      }
    }

    val everything = (enums.keys ++ rawUnions.keys ++ rawInterfaces.keys ++ rawTypes.keys).map(getDefinition)

    Schema(everything.toSeq)
  }

  def parse(input: InputStream): ShorthandResult = {
    val lexer = new GraphQLShorthandLexer(new ANTLRInputStream(input))
    val parser = new GraphQLShorthandParser(new CommonTokenStream(lexer))
    val syntaxErrorListener = new SyntaxErrorListener
    parser.addErrorListener(syntaxErrorListener)

    val defs = parser.schema().definition().asScala

    if (syntaxErrorListener.syntaxErrors.nonEmpty) {
      SyntaxErrors(syntaxErrorListener.syntaxErrors)
    } else {
      try {
        parseFromContexts(defs)
      } catch {
        case UnresolvedException(line, charPos, msg) => UnresolvedError(line, charPos, msg)
      }
    }
  }
}

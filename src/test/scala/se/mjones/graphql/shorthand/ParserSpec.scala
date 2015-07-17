package se.mjones.graphql.shorthand

import java.io.ByteArrayInputStream
import java.nio.charset.Charset

import org.scalatest._

class ParserSpec extends FlatSpec with Matchers {
  lazy val parser = new Parser()

  val UTF8 = Charset.forName("UTF-8")
  def parseString(s: String) = parser.parse(new ByteArrayInputStream(s.getBytes(UTF8)))
  def parseOne(s: String) = parseString(s).asInstanceOf[Schema].definitions.head


  "The Parser" should "be able to parse an enum" in {
    parseString("enum") shouldBe a [SyntaxErrors]
    parseString("enum Johan") shouldBe a [SyntaxErrors]
    parseString("enum Johan { }") shouldBe a [SyntaxErrors]
    parseOne("enum Johan { OPT1 }") shouldBe Enum("Johan", Seq("OPT1"))
    parseOne("enum Johan { OPT1, OPT2 }") shouldBe Enum("Johan", Seq("OPT1", "OPT2"))
  }

  it should "be able to parse an interface" in {
    parseString("interface") shouldBe a [SyntaxErrors]
    parseString("interface Johan") shouldBe a [SyntaxErrors]
    parseString("interface Johan { }") shouldBe a [SyntaxErrors]
    parseOne("interface Johan { name: String }") shouldBe Interface("Johan", Seq(Field("name", GraphQL.String())))
    parseOne("interface Johan { name: String leInt: Int }") shouldBe Interface("Johan", Seq(Field("name", GraphQL.String()), Field("leInt", GraphQL.Int())))
  }

  it should "be able to parse a type" in {
    parseString("type") shouldBe a [SyntaxErrors]
    parseString("type Johan") shouldBe a [SyntaxErrors]
    parseString("type Johan { }") shouldBe a [SyntaxErrors]
    parseOne("type Johan { name: String }") shouldBe Type("Johan", Seq(Field("name", GraphQL.String())))
    parseOne("type Johan { name: String leInt: Int }") shouldBe Type("Johan", Seq(Field("name", GraphQL.String()), Field("leInt", GraphQL.Int())))

    val interfaceSchema = parseString("interface JohansInterface { name: String } type Johan : JohansInterface { name: String leInt: Int }")
    val interfacedType = interfaceSchema.asInstanceOf[Schema].definitions.collectFirst { case t:Type => t}.get
    interfacedType shouldBe Type("Johan",Seq(Field("name",GraphQL.String()), Field("leInt",GraphQL.Int())),Some(Interface("JohansInterface",Seq(Field("name",GraphQL.String())))))
  }

  it should "be able to parse fields" in {
    def parseForField(s: String) = parseOne(s).asInstanceOf[Interface].fields.head

    parseForField("interface Johan { name: String! }") shouldBe Field("name", GraphQL.String(), notNull = true)
    parseForField("interface Johan { name: [String] }") shouldBe Field("name", GraphQL.List(GraphQL.String()))
    parseForField("interface Johan { name: Boolean }") shouldBe Field("name", GraphQL.Boolean())
    parseForField("interface Johan { name: Int }") shouldBe Field("name", GraphQL.Int())
    parseForField("interface Johan { name(param1: Int): Int }") shouldBe Field("name", GraphQL.Int(), parameters = Seq(Parameter("param1", GraphQL.Int())))
    parseForField("interface Johan { name(param1: Int!): Int }") shouldBe Field("name", GraphQL.Int(), parameters = Seq(Parameter("param1", GraphQL.Int(), notNull = true)))
  }

  it should "be able to parse a simple schema" in {
    val parser = new Parser()
    /*
    val schema = parser.parse(getClass.getResourceAsStream("/test.schema"))

    val dogCommand = Enum("DogCommand",Seq("SIT", "DOWN", "HEEL"))
    val pet = Interface("Pet", Seq(Field("name", GraphQL.String(), List(), notNull = true)))
    val sentient = Interface("Sentient", Seq(Field("name",GraphQL.String(), List(), notNull = true)))
    val alien = Type("Alien",Some(sentient),Seq(Field("name",GraphQL.String(),List(),notNull = true), Field("homePlanet",GraphQL.String(),List(),notNull = false)))

    schema.definitions.foreach(println)
*/
  }
}

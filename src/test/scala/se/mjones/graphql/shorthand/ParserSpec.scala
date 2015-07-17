package se.mjones.graphql.shorthand

import java.io.ByteArrayInputStream
import java.nio.charset.Charset

import org.scalatest._

class ParserSpec extends FlatSpec with Matchers {
  lazy val parser = new Parser()

  val UTF8 = Charset.forName("UTF-8")
  def parseString(s: String) = parser.parse(new ByteArrayInputStream(s.getBytes(UTF8)))

  "The Parser" should "be able to parse an enum" in {
    parseString("enum") shouldBe a [SyntaxErrors]
    parseString("enum Johan") shouldBe a [SyntaxErrors]
    parseString("enum Johan { }") shouldBe a [SyntaxErrors]
    parseString("enum Johan { OPT1 }") shouldBe Schema(Seq(Enum("Johan", Seq("OPT1"))))
    parseString("enum Johan { OPT1, OPT2 }") shouldBe Schema(Seq(Enum("Johan", Seq("OPT1", "OPT2"))))
  }

  it should "be able to parse an interface" in {
    parseString("interface") shouldBe a [SyntaxErrors]
    parseString("interface Johan") shouldBe a [SyntaxErrors]
    parseString("interface Johan { }") shouldBe a [SyntaxErrors]
    parseString("interface Johan { OPT1 }") shouldBe Schema(Seq(Enum("Johan", Seq("OPT1"))))
    parseString("interface Johan { OPT1, OPT2 }") shouldBe Schema(Seq(Enum("Johan", Seq("OPT1", "OPT2"))))
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

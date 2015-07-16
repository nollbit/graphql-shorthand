package se.mjones.graphql.shorthand

import org.scalatest._

class ParserSpec extends FlatSpec with Matchers {

  "The Parser" should "be able to parse a simple schema" in {
    val parser = new Parser()
    val schema = parser.parse(getClass.getResourceAsStream("/test.schema"))

    val dogCommand = Enum("DogCommand",Seq("SIT", "DOWN", "HEEL"))
    val pet = Interface("Pet", Seq(Field("name", GraphQL.String(), List(), notNull = true)))
    val sentient = Interface("Sentient", Seq(Field("name",GraphQL.String(), List(), notNull = true)))
    val alien = Type("Alien",Some(sentient),Seq(Field("name",GraphQL.String(),List(),notNull = true), Field("homePlanet",GraphQL.String(),List(),notNull = false)))

    schema.definitions.foreach(println)

  }
}

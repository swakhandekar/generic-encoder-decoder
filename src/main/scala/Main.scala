import basicConversions.BasicDecoder.something
import basicConversions.BasicEncoder.toBasicMap
import basicConversions.DefaultEncoders._
import basicConversions.DefaultDecoders._
import models._

object Main extends App {
  val pune = City("Pune", 23456)
  val cAgent = CompositeAgent("Haresh", 45, pune)

  val basicMapRepresentation = toBasicMap(cAgent)

  println(basicMapRepresentation)

  println("===============================")
  println("===============================")

  private val mapRepr: Map[String, Any] = basicMapRepresentation.toMap
  println(mapRepr)

  println("===============================")
  println("===============================")

  val simpleAgentMap = Map("name" -> "Suresh", "age" -> 56, "someProbability" -> 0.46)
  val x = something[SimpleAgent](simpleAgentMap)
  println(x)
}

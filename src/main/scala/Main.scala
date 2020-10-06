import basicConversions.BasicMapDecoder.decodeMap
import basicConversions.BasicEncoder.toBasicMap
import basicConversions.{BasicDecoder, BasicEncoder, StringValue}
import basicConversions.DefaultEncoders._
import basicConversions.DefaultDecoders._
import models._

object Main extends App {
  implicit val encoder: BasicEncoder[InfectionState] = {
    case Infected => StringValue("Infected")
    case Susceptible => StringValue("Susceptible")
    case Recovered => StringValue("Recovered")
  }

  implicit val basicDecoder: BasicDecoder[InfectionState] = {
    case StringValue(s) => if (s == "Infected") Infected else if (s == "Susceptible") Susceptible else Recovered
  }

  val cAgent = ComplexAgent("Haresh", 45, Infected)

  val basicMapRepresentation = toBasicMap(cAgent)

  println(basicMapRepresentation)

  println("===============================")
  println("===============================")

  private val mapRepr: Map[String, Any] = basicMapRepresentation.toMap
  println(mapRepr)

  println("===============================")
  println("===============================")

  val x = decodeMap[ComplexAgent](mapRepr)
  println(x)
}

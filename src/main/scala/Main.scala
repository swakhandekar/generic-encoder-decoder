import basicConversions.StringValue
import basicConversions.decoders.BasicDecoder
import basicConversions.decoders.BasicMapDecoder.decodeMap
import basicConversions.encoders.BasicEncoder
import basicConversions.encoders.BasicEncoder.toBasicMap
import basicConversions.encoders.DefaultEncoders._
import basicConversions.decoders.DefaultDecoders._
import models._

object Main extends App {
  implicit val encoder: BasicEncoder[InfectionState] = {
    case Infected => StringValue("Infected")
    case Susceptible => StringValue("Susceptible")
    case Recovered => StringValue("Recovered")
  }

  implicit val basicDecoder: BasicDecoder[InfectionState] = {
    case StringValue(s) => if (s == "Infected") Infected else if (s == "Susceptible") Susceptible else Recovered
    case _ => throw new RuntimeException("Will never reach here")
  }

  val city = City("Karad", 34567)
  val cAgent = CompositeAgent("Haresh", 45, city)

  val basicMapRepresentation = toBasicMap(cAgent)

  println(basicMapRepresentation)

  println("===============================")
  println("===============================")

  private val mapRepr: Map[String, Any] = basicMapRepresentation.toMap
  println(mapRepr)

  println("===============================")
  println("===============================")

  val x: CompositeAgent = decodeMap[CompositeAgent](mapRepr)
  println(x)
}

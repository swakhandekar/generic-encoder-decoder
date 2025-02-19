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

  val city = City("Karad", 34567, Map("infected" -> 123, "recovered" -> 567), List("Idk", "what"))
  val cAgent = CompositeAgent("Haresh", 45, city)

  println("Source object: ", cAgent)
  println("\n\nEncoding")

  val basicMapRepresentation = toBasicMap(cAgent)

  println("Basic value representation: ", basicMapRepresentation)

  private val mapRepr: Map[String, Any] = basicMapRepresentation.toMap
  println("Map representation: ", mapRepr)

  println("\n\nDecoding")
  val x = decodeMap[CompositeAgent](mapRepr)
  println("Decoded object:", x)
}

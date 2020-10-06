package basicConversions

import shapeless.HList

trait MapConverter[H] {
  def convert(m: Map[String, Any]): H
}

object MapConverter {
  def apply[T](implicit converter: MapConverter[T]): MapConverter[T] = converter

  def instance[H <: HList](x: Map[String, Any] => H): MapConverter[H] = (m: Map[String, Any]) => x(m)
}

trait BasicDecoder[T] {
  def decode(m: Map[String, Any]): T
}

object BasicDecoder {
  def something[T](m: Map[String, Any])(implicit decoder: BasicDecoder[T]): T = decoder.decode(m)
}


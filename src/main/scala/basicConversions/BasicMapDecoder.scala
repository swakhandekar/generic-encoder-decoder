package basicConversions

import basicConversions.BasicValue.fromMap
import shapeless.HList

trait MapConverter[H] {
  def convert(m: Map[String, BasicValue]): H
}

object MapConverter {
  def apply[T](implicit converter: MapConverter[T]): MapConverter[T] = converter

  def instance[H <: HList](x: Map[String, Any] => H): MapConverter[H] = (m: Map[String, Any]) => x(m)
}

trait BasicDecoder[T] {
  def decode(b: BasicValue): T
}

object BasicDecoder {
  def instance[T](f: BasicValue => T): BasicDecoder[T] = (b: BasicValue) => f(b)
}

trait BasicMapDecoder[T] extends BasicDecoder[T] {
  def decode(b: BasicValue): T
}

object BasicMapDecoder {
  def decodeMap[T](m: Map[String, Any])(implicit decoder: BasicMapDecoder[T]): T = decoder.decode(MapValue(fromMap(m)))

  def apply[T](implicit decoder: BasicMapDecoder[T]): BasicMapDecoder[T] = decoder
}


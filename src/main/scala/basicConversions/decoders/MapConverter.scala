package basicConversions.decoders

import basicConversions.BasicValue
import shapeless.HList

trait MapConverter[H <: HList] {
  def convert(m: Map[String, BasicValue]): H
}

object MapConverter {
  def apply[T <: HList](implicit converter: MapConverter[T]): MapConverter[T] = converter

  def instance[H <: HList](x: Map[String, Any] => H): MapConverter[H] = (m: Map[String, Any]) => x(m)
}
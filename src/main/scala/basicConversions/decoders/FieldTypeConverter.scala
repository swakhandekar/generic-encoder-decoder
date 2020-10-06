package basicConversions.decoders

import basicConversions.BasicValue
import shapeless.labelled.FieldType

trait FieldTypeConverter[K <: Symbol, V] {
  def convert(m: Map[String, BasicValue]): FieldType[K, V]
}

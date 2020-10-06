package basicConversions

import shapeless.labelled.{FieldBuilder, FieldType}
import shapeless.{::, HList, HNil, LabelledGeneric, Lazy, Witness}

object DefaultDecoders {
  implicit val hnilMapConverter: MapConverter[HNil] = (_: Map[String, Any]) => HNil

  implicit def fieldTypeConverterProvider[K <: Symbol, V](implicit witness: Witness.Aux[K]): MapConverter[FieldType[K, V]] = {
    (m: Map[String, Any]) => {
      val fieldName = witness.value.name
      new FieldBuilder[K].apply(m(fieldName).asInstanceOf[V])
    }
  }

  implicit def toHList[K <: Symbol, V, T <: HList](implicit hConverter: Lazy[MapConverter[FieldType[K, V]]],
                                                   tConverter: MapConverter[T]): MapConverter[FieldType[K, V] :: T] = {
    (m: Map[String, Any]) => {
      val head = hConverter.value.convert(m)
      val tail = tConverter.convert(m)
      head :: tail
    }
  }

  implicit def mapDecoder[T, H <: HList](implicit gen: LabelledGeneric.Aux[T, H],
                                         mpToHList: MapConverter[H]): BasicDecoder[T] =
    (m: Map[String, Any]) => gen.from(mpToHList.convert(m))
}

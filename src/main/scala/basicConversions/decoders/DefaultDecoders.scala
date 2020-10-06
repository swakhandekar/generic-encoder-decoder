package basicConversions.decoders

import basicConversions._
import shapeless.labelled.{FieldBuilder, FieldType}
import shapeless.{::, HList, HNil, LabelledGeneric, Lazy, Witness}

object DefaultDecoders {
  implicit val hnilMapConverter: MapConverter[HNil] = (_: Map[String, BasicValue]) => HNil

  implicit val basicStringDecoder: BasicDecoder[String] = {
    case StringValue(v) => v
    case _ => throw new RuntimeException("Cannot convert to String value, implement BasicDecoder to override default")
  }

  implicit val basicIntDecoder: BasicDecoder[Int] = {
    case IntValue(v) => v
    case _ => throw new RuntimeException("Cannot convert to Int value, implement BasicDecoder to override default")
  }

  implicit val basicDoubleDecoder: BasicDecoder[Double] = {
    case DoubleValue(v) => v
    case _ => throw new RuntimeException("Cannot convert to Double value, implement BasicDecoder to override default")
  }

  implicit def fieldTypeConverterProvider[K <: Symbol, V](
                                                           implicit witness: Witness.Aux[K],
                                                           decoder: BasicDecoder[V]): MapConverter[FieldType[K, V]] = {
    (m: Map[String, BasicValue]) => {
      val fieldName = witness.value.name
      new FieldBuilder[K].apply(decoder.decode(m(fieldName)))
    }
  }

  implicit def toHList[K <: Symbol, V, T <: HList](implicit hConverter: Lazy[MapConverter[FieldType[K, V]]],
                                                   tConverter: MapConverter[T]): MapConverter[FieldType[K, V] :: T] = {
    (m: Map[String, BasicValue]) => {
      val head = hConverter.value.convert(m)
      val tail = tConverter.convert(m)
      head :: tail
    }
  }

  implicit def mapDecoder[T, H <: HList](implicit gen: LabelledGeneric.Aux[T, H],
                                         mpToHList: MapConverter[H]): BasicMapDecoder[T] = new BasicMapDecoder[T] {
    override def decode(b: BasicValue): T = {
      b match {
        case x: MapValue => gen.from(mpToHList.convert(x.m))
        case _  => throw new RuntimeException("Failed while creating BasicMapDecoder as provided value is not a MapValue")
      }
    }
  }
}

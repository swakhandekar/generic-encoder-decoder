package basicConversions

sealed trait BasicValue

case class IntValue(v: Int) extends BasicValue
case class FloatValue(v: Float) extends BasicValue
case class DoubleValue(v: Double) extends BasicValue

case class ByteValue(v: Byte) extends BasicValue
case class CharValue(v: Char) extends BasicValue
case class StringValue(v: String) extends BasicValue

case class ListValue(v: List[BasicValue]) extends BasicValue {
  def ++(a: BasicValue): ListValue = copy(a :: v)

  def toList: List[Any] = {
    v.map(x => BasicValue.toSimplifiedValue(x))
  }
}

case class MapValue(m: Map[String, BasicValue]) extends BasicValue {
  def toMap: Map[String, Any] = {
    m.map(kv => {
      val value = kv._2
      val simplifiedValue = BasicValue.toSimplifiedValue(value)
      (kv._1, simplifiedValue)
    })
  }
}

case class NoValue() extends BasicValue

private[basicConversions] object BasicValue {
  def toSimplifiedValue(value: BasicValue): Any = {
    value match {
      case IntValue(v) => v
      case FloatValue(v) => v
      case DoubleValue(v) => v

      case ByteValue(v) => v
      case CharValue(v) => v
      case StringValue(v) => v

      case x: ListValue => x.toList
      case x: MapValue => x.toMap

      case NoValue() => ""
    }
  }
}

package basicConversions

trait BasicMapEncoder[T] extends BasicEncoder[T] {
  def encode(o: T): MapValue
}

object BasicMapEncoder {
  def instance[T](fn: T => MapValue): BasicMapEncoder[T] = (o: T) => fn(o)
}

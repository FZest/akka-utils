package io.github.junheng.akka.utils

object TimestampConvertor {
  final val MILLS = 1000
  final val HOUR = MILLS(3600)
  final val DAY = HOUR(24)

  implicit class int2multi(int: Int) {
    def apply(factor: Int) = int * factor
  }

  implicit class long2timestamp(timestamp: Long) {

    def tsFuzzByHourIn: Long = timestamp / HOUR * HOUR + HOUR

    def tsFuzzByHourOut: Long = timestamp / HOUR * HOUR

    def tsDaysBefore(days: Int): Long = timestamp - DAY * days
  }

  def now = System.currentTimeMillis()

}

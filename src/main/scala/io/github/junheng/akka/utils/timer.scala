package io.github.junheng.akka.utils

import akka.event.LoggingAdapter

object timer {
  def apply[R](msg: String = "", delay: Int = 0)(proc: => R)(implicit log: LoggingAdapter = null): (R, Long) = {
    val timerStart = System.currentTimeMillis
    val r = proc
    val timerStop = System.currentTimeMillis
    val cost = timerStop - timerStart
    if (log != null && cost > delay) log.warning(s"$msg cost $cost ms")
    (r, cost)
  }
}

object cost {
  def apply(proc: => Unit): Long = {
    timer()(proc)._2
  }
}

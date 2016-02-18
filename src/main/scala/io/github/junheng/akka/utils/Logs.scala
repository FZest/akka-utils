package io.github.junheng.akka.utils

import akka.event.slf4j.Logger

/**
  * please use LoggingAdapter instead
  */
@Deprecated
trait Logs {
  lazy val log = Logger(this.getClass.getName)
}

package io.github.junheng.akka.utils

import akka.event.slf4j.Logger
import akka.serialization.Serializer

import scala.pickling.Defaults._
import scala.pickling.binary._
import scala.pickling.shareNothing._

class PicklingSerializer extends Serializer {

  val log = Logger(this.getClass.getCanonicalName)

  override def identifier: Int = 19841023

  override def includeManifest: Boolean = false

  override def fromBinary(bytes: Array[Byte], manifest: Option[Class[_]]): AnyRef = {
    bytes.unpickle[AnyRef]
  }

  override def toBinary(origin: AnyRef): Array[Byte] = {
    origin.pickle.value
  }
}

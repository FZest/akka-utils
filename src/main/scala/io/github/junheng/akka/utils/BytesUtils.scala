package io.github.junheng.akka.utils

import java.util.Date

import org.apache.hadoop.hbase.util.Bytes

import scala.language.implicitConversions
import scala.util.control.Exception._

object BytesUtils extends BytesTrait

trait BytesTrait {

  implicit def string2bytes(str: String): Array[Byte] = Bytes.toBytes(str)

  implicit def bytes2string(bytes: Array[Byte]): String = Bytes.toString(bytes)

  implicit def long2Bytes(in: Long): Array[Byte] = Bytes.toBytes(in)

  implicit def date2Bytes(in: Date): Array[Byte] = Bytes.toBytes(in.getTime)

  implicit def int2Bytes(in: Int): Array[Byte] = Bytes.toBytes(in)

  implicit def float2Bytes(in: Float): Array[Byte] = Bytes.toBytes(in)

  implicit def double2Bytes(in: Double): Array[Byte] = Bytes.toBytes(in)

  implicit def boolean2Bytes(in: Boolean): Array[Byte] = Bytes.toBytes(in)

  implicit def bytes2Long(bytes: Array[Byte]): Long = Bytes.toLong(bytes)

  implicit class Bytes2Other(val s: Array[Byte]) {
    def toIntOpt = catching(classOf[Exception]) opt Bytes.toInt(s)

    def toLongOpt = catching(classOf[Exception]) opt Bytes.toLong(s)

    def toDoubleOpt = catching(classOf[Exception]) opt Bytes.toDouble(s)

    def toFloatOpt = catching(classOf[Exception]) opt Bytes.toFloat(s)
  }

  implicit class String2Other(val s: String) {

    def toIntOpt = catching(classOf[Exception]) opt s.toInt

    def toLongOpt = catching(classOf[Exception]) opt s.toLong

    def toDoubleOpt = catching(classOf[Exception]) opt s.toDouble

    def toFloatOpt = catching(classOf[Exception]) opt s.toFloat
  }
}

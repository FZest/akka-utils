package io.github.junheng.akka.utils

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import java.util.zip.{GZIPInputStream, GZIPOutputStream}

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream

import scala.util.control.Exception.catching

object gzip {
  private final val BUFFER: Int = 1024

  def zip(input: Array[Byte]): Either[Throwable, Array[Byte]] = {
    catching(classOf[Throwable]).either {
      val bos = new ByteOutputStream()
      val gos = new GZIPOutputStream(bos)
      gos.write(input)
      gos.finish()
      gos.close()
      bos.close()
      bos.getBytes
    }
  }

  def unzip(gzipData: Array[Byte]): Either[Throwable, Array[Byte]] = {
    catching(classOf[Throwable]).either {
      val gis = new GZIPInputStream(new ByteArrayInputStream(gzipData))
      var count: Int = 0
      val byteArray = new Array[Byte](BUFFER)
      val bos = new ByteArrayOutputStream()
      do {
        bos.write(byteArray, 0, count)
        count = gis.read(byteArray)
      } while (count != -1)
      val postByteArray = bos.toByteArray
      gis.close()
      bos.close()
      postByteArray
    }
  }


}

package io.github.junheng.akka.utils.redis

import akka.event.LoggingAdapter
import com.wandoulabs.jodis.RoundRobinJedisPool
import redis.clients.jedis.{JedisPoolConfig, Jedis}

object jodis {
  var log: LoggingAdapter = _
  var pool: RoundRobinJedisPool = null

  def init(db: String, zk: String, zkTimeout: Int, _log: LoggingAdapter) = {
    log = _log
    val config = new JedisPoolConfig()
    config.setMaxTotal(1024)
    config.setMaxIdle(128)
    config.setMaxWaitMillis(-1)
    config.setBlockWhenExhausted(true)
    jodis.synchronized {
      pool = RoundRobinJedisPool
        .create()
        .poolConfig(config)
        .curatorClient(zk, zkTimeout)
        .timeoutMs(12000)
        .zkProxyDir(s"/zk/codis/$db/proxy")
        .build()
    }
  }

  def apply[T: Manifest](process: Jedis => T): Option[T] = {
    try {
      val resource = pool.getResource
      try Option(process(resource)) catch {
        case ex: Throwable =>
          log.error(ex, "failed in jodis brace")
          None
      } finally {
        resource.close()
      }
    } catch {
      case ex: Throwable =>
        log.error(ex, "failed to get jodis resource")
        None
    }
  }
}

package io.github.junheng.akka.utils.redis

import com.redis.{RedisClient, RedisClientPool}

object jedis {
  val pool = new RedisClientPool("10.161.224.93", 22121)

  def apply[T: Manifest](execute: RedisClient => T): T = {
    pool.withClient[T](execute)
  }
}
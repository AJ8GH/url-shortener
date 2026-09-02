package io.github.aj8gh.urlshortener.service

import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.support.atomic.RedisAtomicLong
import org.springframework.stereotype.Service

@Service
class AtomicCounterService(
  private val factory: RedisConnectionFactory,
  private val counter: RedisAtomicLong = RedisAtomicLong("global_counter", factory),
) {

  fun incrementAndGet() = counter.incrementAndGet()
}

package io.github.aj8gh.urlshortener.componenttest.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import redis.embedded.RedisServer

@TestConfiguration
class RedisConfig(
  @Value("\${spring.data.redis.port}") private val port: Int,
  private val server: RedisServer = RedisServer(port),
) {

  @PostConstruct
  fun postConstruct() {
    server.start()
  }

  @PreDestroy
  fun preDestroy() {
    server.stop()
  }
}

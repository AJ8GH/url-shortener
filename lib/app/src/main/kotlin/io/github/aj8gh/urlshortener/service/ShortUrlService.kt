package io.github.aj8gh.urlshortener.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ShortUrlService(
  private val counter: AtomicCounterService,
  @Value("\${app.scheme}") private val scheme: String,
  @Value("\${app.host}") private val host: String,
) {
  fun create(longUrl: String) = counter.incrementAndGet().toString().let {
    "$scheme://$host/$it"
  }
}

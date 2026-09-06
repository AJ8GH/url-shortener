package io.github.aj8gh.urlshortener.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class BaseUrlProvider(
  @Value("\${app.scheme}") private val scheme: String,
  @Value("\${app.host}") private val host: String,
) {

  fun get() = "$scheme://$host"
}

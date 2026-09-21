package io.github.aj8gh.urlshortener.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class BaseUrlProvider(
  @Value("\${app.api.scheme}") private val scheme: String,
  @Value("\${app.api.host}") private val host: String,
) {

  fun get() = "$scheme://$host"
}

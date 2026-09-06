package io.github.aj8gh.urlshortener.service.model

import java.time.Instant

data class UrlMapping(
  val shortUrlPath: String,
  val shortBaseUrl: String,
  val longUrl: String,
  val expiresAt: Instant? = null,
)

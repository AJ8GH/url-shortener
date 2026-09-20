package io.github.aj8gh.urlshortener.api.model

import io.github.aj8gh.urlshortener.service.model.UrlMapping
import java.time.Instant

data class ShortUrlResponse(
  val longUrl: String,
  val shortUrl: String,
  val expiresAt: Instant? = null,
)

fun toResponse(model: UrlMapping) = ShortUrlResponse(
  longUrl = model.longUrl,
  shortUrl = "${model.shortBaseUrl}/${model.shortUrlPath}",
  expiresAt = model.expiresAt,
)

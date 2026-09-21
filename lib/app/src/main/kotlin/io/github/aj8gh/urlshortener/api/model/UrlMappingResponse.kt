package io.github.aj8gh.urlshortener.api.model

import io.github.aj8gh.urlshortener.service.model.UrlMapping
import java.time.Instant

data class UrlMappingResponse(
  val longUrl: String,
  val shortUrl: String,
  val expiresAt: Instant? = null,
)

fun toResponse(model: UrlMapping) = UrlMappingResponse(
  longUrl = model.longUrl,
  shortUrl = "${model.shortBaseUrl}/${model.shortUrlPath}",
  expiresAt = model.expiresAt,
)

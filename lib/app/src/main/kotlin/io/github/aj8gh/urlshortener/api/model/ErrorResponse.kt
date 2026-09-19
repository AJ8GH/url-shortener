package io.github.aj8gh.urlshortener.api.model

import java.time.Instant
import java.util.*

data class ErrorResponse(
  val id: UUID,
  val message: String,
  val timestamp: Instant,
)

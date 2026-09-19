package io.github.aj8gh.urlshortener.exception

class ResourceNotFoundException(
  override val message: String,
  override val cause: Throwable? = null,
) : RuntimeException(message, cause)

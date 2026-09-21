package io.github.aj8gh.urlshortener.api.advice

import io.github.aj8gh.urlshortener.api.model.ErrorResponse
import io.github.aj8gh.urlshortener.exception.ResourceNotFoundException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Clock
import java.util.*
import java.util.UUID.randomUUID

val log = KotlinLogging.logger { }

@RestControllerAdvice
class ControllerAdvice(
  private val clock: Clock,
) {

  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler(
    value = [ResourceNotFoundException::class],
    produces = [APPLICATION_JSON_VALUE]
  )
  fun handleResourceNotFound(e: ResourceNotFoundException) = handle(e)

  private fun handle(e: Exception) = ErrorResponse(
    id = randomUUID(),
    message = e.message ?: "No message available",
    timestamp = clock.instant(),
  ).also {
    log.error {
      "Handling exception=${e::class}, id=${it.id}, message='${e.message}'"
    }
  }
}

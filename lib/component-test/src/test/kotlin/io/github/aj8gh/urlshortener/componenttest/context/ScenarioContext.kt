package io.github.aj8gh.urlshortener.componenttest.context

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class ScenarioContext(
  var response: ResponseEntity<*>? = null,
) {

  @Suppress("UNCHECKED_CAST")
  fun <T : Any> body() = response!!.body as T

  fun status() = response!!.statusCode
}

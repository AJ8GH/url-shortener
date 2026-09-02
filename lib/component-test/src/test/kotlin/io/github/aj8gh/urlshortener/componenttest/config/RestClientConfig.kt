package io.github.aj8gh.urlshortener.componenttest.config

import io.github.aj8gh.urlshortener.componenttest.cucumber.TEST_PROFILE
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpHeaders.ACCEPT
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.client.RestClient

private const val HOST = "http://localhost"

@Lazy
@Profile(TEST_PROFILE)
@TestConfiguration
class RestClientConfig(
  @LocalServerPort private val port: Int,
  @Value("\${server.servlet.context-path}") private val contextPath: String,
) {

  @Bean
  fun restClient(): RestClient = RestClient.builder()
    .baseUrl("$HOST:$port/$contextPath")
    .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
    .defaultHeader(ACCEPT, APPLICATION_JSON_VALUE)
    .build()
}

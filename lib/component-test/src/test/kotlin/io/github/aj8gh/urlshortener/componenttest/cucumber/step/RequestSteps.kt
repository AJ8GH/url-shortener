package io.github.aj8gh.urlshortener.componenttest.cucumber.step

import io.cucumber.java.en.When
import io.github.aj8gh.urlshortener.api.controller.SHORT_URL_PATH
import io.github.aj8gh.urlshortener.api.controller.SHORT_URL_PATH_PARAM
import io.github.aj8gh.urlshortener.api.controller.SHORT_URL_PATH_WITH_PARAM
import io.github.aj8gh.urlshortener.api.model.ErrorResponse
import io.github.aj8gh.urlshortener.api.model.UrlMappingRequest
import io.github.aj8gh.urlshortener.api.model.ShortUrlResponse
import io.github.aj8gh.urlshortener.componenttest.context.ScenarioContext
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatusCode
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.RestClient

val log = KotlinLogging.logger { }

class RequestSteps(
  private val client: RestClient,
  private val context: ScenarioContext,
) {

  @When("I make request to get url mapping for path {}")
  fun getShortUrl(path: String) {
    client.get()
      .uri(SHORT_URL_PATH_WITH_PARAM, path)
      .retrieve()
      .toEntity(ShortUrlResponse::class.java)
      .also { context.response = it }
  }

  @When("I make request to get non-existent url mapping for path {}")
  fun getNonExistentShortUrl(path: String) {
    client.get()
      .uri(SHORT_URL_PATH_WITH_PARAM, path)
      .retrieve()
      .onStatus(HttpStatusCode::isError, this::errorHandler)
      .toEntity(ErrorResponse::class.java)
      .also { context.response = it }
  }

  @When("I make request to create short url for {}")
  fun createShortUrl(longUrl: String) {
    client.post()
      .uri(SHORT_URL_PATH)
      .body(UrlMappingRequest(longUrl))
      .retrieve()
      .toEntity(ShortUrlResponse::class.java)
      .also { context.response = it }
  }

  @When("I make request to delete url mapping for path {}")
  fun deleteShortUrl(path: String) {
    client.delete()
      .uri(SHORT_URL_PATH_WITH_PARAM, path)
      .retrieve()
      .toEntity(Void::class.java)
      .also { context.response = it }
  }

  @When("I make request to access url mapping for path {}")
  fun accessShortUrl(path: String) {
    client.get()
      .uri(SHORT_URL_PATH_PARAM, path)
      .retrieve()
      .toEntity(String::class.java)
      .also { context.response = it }
  }

  private fun errorHandler(
    req: HttpRequest,
    res: ClientHttpResponse,
  ) = log.error {
    """Rest client HTTP error, url=${req.uri}, method=${req.method}, statusCode=${res.statusCode} : ${res.statusText}, responseBody=${res.body}"""
  }
}

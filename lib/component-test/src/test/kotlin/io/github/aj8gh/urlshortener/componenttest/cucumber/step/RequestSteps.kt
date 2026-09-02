package io.github.aj8gh.urlshortener.componenttest.cucumber.step

import io.cucumber.java.en.When
import io.github.aj8gh.urlshortener.api.controller.SHORT_URL_PATH
import io.github.aj8gh.urlshortener.api.model.ShortUrlRequest
import io.github.aj8gh.urlshortener.api.model.ShortUrlResponse
import io.github.aj8gh.urlshortener.componenttest.context.ScenarioContext
import org.springframework.web.client.RestClient

class RequestSteps(
  private val client: RestClient,
  private val context: ScenarioContext,
) {

  @When("I make request to create short url for {}")
  fun createShortUrl(longUrl: String) {
    client.post()
      .uri(SHORT_URL_PATH)
      .body(ShortUrlRequest(longUrl))
      .retrieve()
      .toEntity(ShortUrlResponse::class.java)
      .also { context.response = it }
  }
}

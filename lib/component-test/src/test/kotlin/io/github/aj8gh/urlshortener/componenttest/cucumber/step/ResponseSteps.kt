package io.github.aj8gh.urlshortener.componenttest.cucumber.step

import io.cucumber.java.en.Then
import io.github.aj8gh.urlshortener.api.model.ShortUrlResponse
import io.github.aj8gh.urlshortener.componenttest.context.ScenarioContext
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus

class ResponseSteps(
  private val context: ScenarioContext,
  @Value("\${app.scheme}") private val scheme: String,
  @Value("\${app.host}") private val host: String,
) {

  @Then("the response status code is {}")
  fun statusCodeIs(expected: Int) {
    context.status() shouldBe HttpStatus.valueOf(expected)
  }

  @Then("the response contains a short url with path {}")
  fun shortUrlIs(expected: String) {
    context.body<ShortUrlResponse>().shortUrl shouldBeEqual
        "$scheme://$host$expected"
  }
}

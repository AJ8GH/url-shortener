package io.github.aj8gh.urlshortener.componenttest.cucumber.step

import io.cucumber.java.en.Then
import io.github.aj8gh.urlshortener.api.model.ErrorResponse
import io.github.aj8gh.urlshortener.api.model.ShortUrlResponse
import io.github.aj8gh.urlshortener.componenttest.context.ScenarioContext
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
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

  @Then("the response contains url mapping {} to path {}")
  fun responseContainsUrlMapping(longUrl: String, shortPath: String) {
    context.body<ShortUrlResponse>().let {
      it.shortUrl shouldBeEqual "$scheme://$host/$shortPath"
      it.longUrl shouldBeEqual longUrl
    }
  }

  @Then("the response contains error message {}")
  fun errorMessageIs(expected: String) {
    context.body<ErrorResponse>().let {
      it.message shouldBeEqual expected
      shouldNotBeNull { it.id }
      shouldNotBeNull { it.timestamp }
    }
  }
}

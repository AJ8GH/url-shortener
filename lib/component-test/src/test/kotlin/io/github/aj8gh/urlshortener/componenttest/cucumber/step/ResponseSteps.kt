package io.github.aj8gh.urlshortener.componenttest.cucumber.step

import io.cucumber.java.en.Then
import io.github.aj8gh.urlshortener.api.model.ErrorResponse
import io.github.aj8gh.urlshortener.api.model.UrlMappingResponse
import io.github.aj8gh.urlshortener.componenttest.context.ScenarioContext
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus

class ResponseSteps(
  private val context: ScenarioContext,
  @Value("\${app.api.scheme}") private val scheme: String,
  @Value("\${app.api.host}") private val host: String,
) {

  @Then("the response status code is {}")
  fun statusCodeIs(expected: Int) {
    context.status() shouldBe HttpStatus.valueOf(expected)
  }

  @Then("the response contains url-mapping {} to path {}")
  fun responseContainsUrlMapping(longUrl: String, shortPath: String) {
    context.body<UrlMappingResponse>().let {
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

  @Then("the response contains header {}: {}")
  fun responseContainsHeader(header: String, value: String) {
    context.response!!.headers.get(header)!! shouldContainExactly listOf(value)
  }
}

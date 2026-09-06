package io.github.aj8gh.urlshortener.componenttest.cucumber.step

import io.cucumber.java.en.Then
import io.github.aj8gh.urlshortener.componenttest.context.ScenarioContext
import io.github.aj8gh.urlshortener.persistence.UrlMappingRepository
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import org.springframework.beans.factory.annotation.Value

class DatabaseSteps(
  private val context: ScenarioContext,
  private val repository: UrlMappingRepository,
  @Value("\${app.scheme}") private val scheme: String,
  @Value("\${app.host}") private val host: String,
) {

  @Then("url mapping {} to path {} is persisted")
  fun urlMappingPersisted(longUrl: String, shortPath: String) {
    val actual = repository.findById(shortPath).orElseThrow()
    actual.longUrl shouldBeEqual longUrl
    actual.shortUrlPath shouldBeEqual shortPath
    shouldNotBeNull { actual.createdAt }
    shouldNotBeNull { actual.updatedAt }
  }
}

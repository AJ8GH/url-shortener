package io.github.aj8gh.urlshortener.componenttest.cucumber.step

import io.cucumber.java.en.Given
import io.github.aj8gh.urlshortener.service.AtomicCounterService

class CounterSteps(
  private val counter: AtomicCounterService,
) {

  @Given("{long} url-mappings are created")
  fun setCounter(count: Long) {
    counter.set(count)
  }
}

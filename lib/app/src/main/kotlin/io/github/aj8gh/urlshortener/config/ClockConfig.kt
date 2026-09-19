package io.github.aj8gh.urlshortener.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
class ClockConfig {

  @Bean
  fun clock() = Clock.systemUTC()
}

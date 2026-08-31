package io.github.aj8gh.urlshortener

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UrlShortenerApp

fun main(args: Array<String>) {
  runApplication<UrlShortenerApp>(*args)
}

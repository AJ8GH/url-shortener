package io.github.aj8gh.urlshortener.api.controller

import io.github.aj8gh.urlshortener.api.model.ShortUrlRequest
import io.github.aj8gh.urlshortener.api.model.toResponse
import io.github.aj8gh.urlshortener.service.ShortUrlService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

const val SHORT_URL_PATH = "/short-url"

@RestController
@RequestMapping(SHORT_URL_PATH)
@ResponseStatus(CREATED)
class ShortUrlController(
  private val service: ShortUrlService,
) {

  @PostMapping
  fun create(@RequestBody request: ShortUrlRequest) =
    toResponse(service.create(request.longUrl))
}

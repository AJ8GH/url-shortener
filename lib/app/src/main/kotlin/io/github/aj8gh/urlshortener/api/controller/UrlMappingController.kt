package io.github.aj8gh.urlshortener.api.controller

import io.github.aj8gh.urlshortener.api.model.UrlMappingRequest
import io.github.aj8gh.urlshortener.api.model.toResponse
import io.github.aj8gh.urlshortener.service.UrlMappingService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

const val SHORT_URL_PATH = "/short-url"
const val SHORT_URL_PATH_PARAM = "/{path}"
const val SHORT_URL_PATH_WITH_PARAM = "$SHORT_URL_PATH$SHORT_URL_PATH_PARAM"

@RestController
@RequestMapping(SHORT_URL_PATH)
class UrlMappingController(
  private val service: UrlMappingService,
) {

  @PostMapping
  @ResponseStatus(CREATED)
  fun create(@RequestBody request: UrlMappingRequest) =
    toResponse(service.create(request.longUrl))

  @GetMapping(SHORT_URL_PATH_PARAM)
  fun get(@PathVariable path: String) =
    toResponse(service.get(path))

  @DeleteMapping(SHORT_URL_PATH_PARAM)
  @ResponseStatus(NO_CONTENT)
  fun delete(@PathVariable path: String) =
    service.delete(path)
}

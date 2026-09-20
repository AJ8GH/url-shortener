package io.github.aj8gh.urlshortener.api.controller

import io.github.aj8gh.urlshortener.api.model.UrlMappingRequest
import io.github.aj8gh.urlshortener.api.model.toResponse
import io.github.aj8gh.urlshortener.service.UrlMappingService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpHeaders.LOCATION
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.FOUND
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap.fromSingleValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(SHORT_URL_PATH_PARAM)
class AccessController(
  private val service: UrlMappingService,
) {

  @PostMapping
  @ResponseStatus(CREATED)
  fun create(@RequestBody request: UrlMappingRequest) =
    toResponse(service.create(request.longUrl))

  @GetMapping
  @ResponseStatus(FOUND)
  fun access(@PathVariable path: String): ResponseEntity<Unit> {
    val longUrl = service.get(path).longUrl
    val headers = HttpHeaders(fromSingleValue(mapOf(Pair(LOCATION, longUrl))))
    return ResponseEntity(headers, FOUND)
  }
}

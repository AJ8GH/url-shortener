package io.github.aj8gh.urlshortener.service

import io.github.aj8gh.urlshortener.exception.ResourceNotFoundException
import io.github.aj8gh.urlshortener.persistence.model.fromEntity
import io.github.aj8gh.urlshortener.persistence.model.toEntity
import io.github.aj8gh.urlshortener.persistence.repository.UrlMappingRepository
import io.github.aj8gh.urlshortener.service.model.UrlMapping
import org.springframework.stereotype.Service

@Service
class UrlMappingService(
  private val counter: AtomicCounterService,
  private val repository: UrlMappingRepository,
  private val baseUrlProvider: BaseUrlProvider,
  private val encodingService: EncodingService,
) {

  fun create(longUrl: String) =
    counter.incrementAndGet()
      .let { encodingService.encode(it) }
      .let {
        UrlMapping(
          shortUrlPath = it,
          shortBaseUrl = baseUrlProvider.get(),
          longUrl = longUrl,
        )
      }
      .also { repository.saveAndFlush(toEntity(it)) }

  fun get(shortUrl: String) = repository.findById(shortUrl)
    .orElseThrow {
      ResourceNotFoundException("no url-mapping found for short-url path $shortUrl")
    }.let { fromEntity(it, baseUrlProvider.get()) }

  fun delete(shortUrl: String) = repository.deleteById(shortUrl)
}

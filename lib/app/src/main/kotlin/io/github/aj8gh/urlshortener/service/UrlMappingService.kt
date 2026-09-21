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
) {

  fun create(longUrl: String): UrlMapping {
    val shortPath = counter.incrementAndGet().toString()
    val mapping = UrlMapping(
      shortUrlPath = shortPath,
      shortBaseUrl = baseUrlProvider.get(),
      longUrl = longUrl,
    )
    repository.saveAndFlush(toEntity(mapping))
    return mapping
  }

  fun get(shortUrl: String) = repository.findById(shortUrl)
    .orElseThrow {
      ResourceNotFoundException("no url-mapping found for short-url path $shortUrl")
    }.let { fromEntity(it, baseUrlProvider.get()) }

  fun delete(shortUrl: String) = repository.deleteById(shortUrl)
}

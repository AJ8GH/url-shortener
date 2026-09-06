package io.github.aj8gh.urlshortener.service

import io.github.aj8gh.urlshortener.persistence.UrlMappingRepository
import io.github.aj8gh.urlshortener.persistence.model.toEntity
import io.github.aj8gh.urlshortener.service.model.UrlMapping
import org.springframework.stereotype.Service

@Service
class ShortUrlService(
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
    repository.save(toEntity(mapping))
    return mapping
  }
}

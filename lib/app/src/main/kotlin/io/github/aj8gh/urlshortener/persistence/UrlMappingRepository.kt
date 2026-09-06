package io.github.aj8gh.urlshortener.persistence

import io.github.aj8gh.urlshortener.persistence.model.UrlMappingEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UrlMappingRepository : JpaRepository<UrlMappingEntity, String>

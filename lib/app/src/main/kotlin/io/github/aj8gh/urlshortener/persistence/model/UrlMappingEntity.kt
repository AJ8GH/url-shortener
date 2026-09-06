package io.github.aj8gh.urlshortener.persistence.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity(name = "url_mapping")
data class UrlMappingEntity(
  @Id
  val shortUrlPath: String,
  val longUrl: String,
  val expiresAt: Instant = Instant.MAX,
  @CreationTimestamp
  @Column(updatable = false, nullable = false)
  val createdAt: Instant? = null,
  @Column(nullable = false)
  @UpdateTimestamp
  val updatedAt: Instant? = null,
) {
  override fun equals(other: Any?) = this === other
      || (other is UrlMappingEntity && shortUrlPath == other.shortUrlPath)

  override fun hashCode() = shortUrlPath.hashCode()

  override fun toString() =
    "(shortUrlPath=$shortUrlPath, longUrl=$longUrl, expiresAt=$expiresAt)"
}

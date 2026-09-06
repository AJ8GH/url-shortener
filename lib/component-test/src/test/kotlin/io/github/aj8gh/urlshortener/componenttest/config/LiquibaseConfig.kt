package io.github.aj8gh.urlshortener.componenttest.config

import io.github.oshai.kotlinlogging.KotlinLogging
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

private val log = KotlinLogging.logger { }

@Configuration
class LiquibaseConfig {

  @Bean
  fun liquibaseInitializer(dataSource: DataSource): String {
    log.info { "=== initialising liquibase ===" }
    dataSource.connection.use { connection ->
      log.info { "=== acquiring db connection for liquibase update ===" }
      val database = DatabaseFactory.getInstance()
        .findCorrectDatabaseImplementation(JdbcConnection(connection))

      val liquibase = Liquibase(
        "db/changelog/db.changelog-master.yaml",
        ClassLoaderResourceAccessor(),
        database
      )
      // This manually bypasses any missing auto-configuration hooks
      log.info { "=== running liquibase update ===" }
      liquibase.update("")
    }
    log.info { "=== liquibase forced successfully ===" }
    return "liquibase-forced-successfully"
  }
}

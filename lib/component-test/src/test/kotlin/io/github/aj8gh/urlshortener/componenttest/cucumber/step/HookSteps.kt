package io.github.aj8gh.urlshortener.componenttest.cucumber.step

import io.cucumber.java.Before
import io.github.aj8gh.urlshortener.service.AtomicCounterService
import org.springframework.jdbc.core.JdbcTemplate

class HookSteps(
  private val jdbcRepository: JdbcTemplate,
  private val atomicCounterService: AtomicCounterService,
) {

  @Before
  fun before() {
    clearTables()
    atomicCounterService.reset()
  }

  private fun clearTables() {
    val tables = jdbcRepository.queryForList(
      "select tablename from pg_tables where schemaname = 'public'",
      String::class.java
    ).joinToString(", ") { "public.$it" }
    jdbcRepository.execute("truncate table $tables restart identity cascade")
  }
}

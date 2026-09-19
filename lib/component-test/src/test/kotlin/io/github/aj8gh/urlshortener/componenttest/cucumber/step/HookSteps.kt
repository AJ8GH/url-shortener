package io.github.aj8gh.urlshortener.componenttest.cucumber.step

import io.cucumber.java.Before
import io.github.aj8gh.urlshortener.service.AtomicCounterService
import org.springframework.jdbc.core.JdbcTemplate

private const val GET_TABLE_NAMES =
  "select tablename from pg_tables where schemaname = 'public'"

class HookSteps(
  private val template: JdbcTemplate,
  private val counter: AtomicCounterService,
) {

  @Before
  fun before() {
    clearTables()
    counter.reset()
  }

  private fun clearTables() = GET_TABLE_NAMES
    .let { template.queryForList(it, String::class.java) }
    .joinToString(", ") { "public.$it" }
    .let { template.execute("truncate table $it restart identity cascade") }
}

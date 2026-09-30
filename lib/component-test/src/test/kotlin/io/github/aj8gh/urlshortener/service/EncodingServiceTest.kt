package io.github.aj8gh.urlshortener.service

import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class EncodingServiceTest {

  private val subject = EncodingService()

  companion object {
    @JvmStatic
    private fun data() = listOf(
      Arguments.of(10, "A"),
      Arguments.of(30, "U"),
      Arguments.of(61, "z"),
      Arguments.of(65, "13"),
      Arguments.of(723, "Bf"),
      Arguments.of(12239823, "pM8V"),
      Arguments.of(98765432123456789, "7ILUojvWNx")
    )
  }

  @ParameterizedTest
  @MethodSource("data")
  fun encode(input: Long, expected: String) {
    subject.encode(input) shouldBeEqual expected
  }
}

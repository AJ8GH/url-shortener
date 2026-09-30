package io.github.aj8gh.urlshortener.service

import org.springframework.stereotype.Service

private const val ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"

@Service
class EncodingService {

  fun encode(count: Long): String {
    var currentCount = count
    val result = StringBuilder()
    while (currentCount > 0) {
      val currentVal = currentCount % ALPHABET.length
      result.append(ALPHABET[currentVal.toInt()])
      currentCount /= ALPHABET.length
    }
    return result.reversed().toString()
  }
}

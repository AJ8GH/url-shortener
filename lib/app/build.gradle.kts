plugins {
  alias(libs.plugins.kotlin.jpa)
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.kotlin.spring)
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependencyManagement)
  alias(libs.plugins.kover)
}

dependencies {
  implementation(libs.bundles.app)
}

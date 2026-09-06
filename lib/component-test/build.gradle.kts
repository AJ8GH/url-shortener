plugins {
  alias(libs.plugins.kotlin.jpa)
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.kotlin.spring)
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependencyManagement)
  alias(libs.plugins.kover)
}

dependencies {
  testImplementation(platform(rootProject.libs.cucumber.bom))
  testImplementation(rootProject.libs.bundles.componentTest)
  testImplementation(rootProject.libs.bundles.app)
  testImplementation(project(":lib:app"))
}

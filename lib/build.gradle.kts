plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependencyManagement)
  alias(libs.plugins.kotlin.spring)
  alias(libs.plugins.kover)
}

dependencies {
  testImplementation(libs.junit.jupiter)
  testRuntimeOnly(libs.junit.platform.launcher)
}

kotlin {
  jvmToolchain(rootProject.libs.versions.java.get().toInt())
}

allprojects {
  group = extra["project.group"] as String
  version = extra["project.version"] as String

  repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
  }
}

tasks.named<Test>("test") {
  useJUnitPlatform()
}

tasks.bootJar {
  enabled = false
}

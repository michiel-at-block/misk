import com.vanniktech.maven.publish.JavadocJar.Dokka
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension

plugins {
  kotlin("jvm")
  `java-library`
  id("com.vanniktech.maven.publish.base")
}

apply(plugin = "org.jetbrains.kotlin.plugin.allopen")
apply(plugin = "kotlin-jpa")

configure<AllOpenExtension> {
  annotation("javax.persistence.Entity")
  annotation("javax.persistence.Embeddable")
  annotation("javax.persistence.MappedSuperclass")
}

sourceSets {
  val test by getting {
    java.srcDir("src/test/kotlin/")
  }
}

dependencies {
  api(Dependencies.guava)
  api(Dependencies.guice)
  api(Dependencies.hibernateCore)
  api(Dependencies.jakartaInject)
  api(Dependencies.javaxPersistenceApi)
  api(project(":misk-inject"))
  api(project(":misk-jdbc"))
  implementation(Dependencies.kotlinLogging)
  implementation(Dependencies.kotlinReflect)
  implementation(Dependencies.moshi)
  implementation(Dependencies.okHttp)
  implementation(Dependencies.okio)
  implementation(Dependencies.slf4jApi)
  implementation(Dependencies.tink)
  implementation(Dependencies.tinkAwskms)
  implementation(Dependencies.tinkGcpkms)
  implementation(Dependencies.wireRuntime)
  implementation(project(":wisp:wisp-logging"))
  implementation(project(":misk"))
  implementation(project(":misk-action-scopes"))
  implementation(project(":misk-actions"))
  implementation(project(":misk-admin"))
  implementation(project(":misk-core"))
  implementation(project(":misk-crypto"))
  implementation(project(":misk-service"))

  testImplementation(Dependencies.assertj)
  testImplementation(Dependencies.junitApi)
  testImplementation(Dependencies.kotlinTest)
  testImplementation(Dependencies.logbackClassic)
  testImplementation(Dependencies.mockitoCore)
  testImplementation(Dependencies.prometheusClient)
  testImplementation(project(":wisp:wisp-config"))
  testImplementation(project(":wisp:wisp-deployment"))
  testImplementation(project(":wisp:wisp-logging-testing"))
  testImplementation(project(":wisp:wisp-time-testing"))
  testImplementation(project(":misk-config"))
  testImplementation(project(":misk-hibernate-testing"))
  testImplementation(testFixtures(project(":misk-jdbc")))
  testImplementation(project(":misk-metrics"))
  testImplementation(project(":misk-testing"))
  testImplementation(testFixtures(project(":misk-crypto")))
}

configure<MavenPublishBaseExtension> {
  configure(
    KotlinJvm(javadocJar = Dokka("dokkaGfm"))
  )
}

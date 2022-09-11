repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

plugins {
    // Support convention plugins written in Kotlin.
    // Convention plugins are build scripts in 'src/main'
    // that automatically become available as plugins in the main build.
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

val versions = org.jetbrains.kotlin.konan.properties.loadProperties(
    projectDir.resolve("../versions.properties").toString()
)
// In order to use a dependency in buildSrc,
// first add it in the root build.gradle.kts buildscript to get it managed by refreshVersions.
dependencies {
    implementation(gradleApi())
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("com.android.tools.build:gradle:${versions["plugin.android"]}")
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    // https://docs.gradle.org/current/userguide/plugins.html#sec:plugin_version_management
    val versions = org.jetbrains.kotlin.konan.properties.loadProperties(
        projectDir.resolve("../versions.properties").toString()
    )
    plugins {
        kotlin("jvm") version "${versions["version.kotlin"]}"
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
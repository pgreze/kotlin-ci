pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

plugins {
    id("de.fayard.refreshVersions") version "0.40.2"
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "kotlin-ci"

include("app")
include("core")
include("domain")
include("scripts")
// Only load scripts folder if required.
//val includeScripts: String? by settings
//if ("true" in arrayOf(System.getenv("CI"), includeScripts)) {
//    includeBuild("scripts")
//}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies { // Allowing to populate versions.properties, used in buildSrc
        classpath(Android.tools.build.gradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
    }
}

tasks.withType<Delete> {
    delete(rootProject.buildDir)
}

// Integrates :scripts with existing tasks

tasks.named("refreshVersions").configure {
    dependsOn("scripts:backupVersions")
    finalizedBy("scripts:stripAvailableVersions")
}

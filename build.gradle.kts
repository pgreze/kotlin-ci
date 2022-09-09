//plugins {
//    id("com.android.application") version "7.3.0-rc01" apply false
//    id("com.android.library") version "7.3.0-rc01" apply false
//    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
//}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies { // Allowing to populate versions.properties, used in buildSrc
        classpath("com.android.tools.build:gradle:_")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
    }
}

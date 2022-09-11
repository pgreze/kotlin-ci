package com.github.pgreze.kotlinci

plugins {
    id("com.android.application")
    id("com.github.pgreze.kotlinci.android-common")
}

configure<com.android.build.gradle.AppExtension> {
    setCompileSdkVersion(Config.compileSdk)
    namespace = "com.github.pgreze.kotlinci"

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

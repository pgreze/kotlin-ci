package com.github.pgreze.kotlinci

plugins {
    // id("com.android.base")
    id("org.jetbrains.kotlin.android")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    "implementation"(platform("org.jetbrains.kotlin:kotlin-bom"))
    "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    "testImplementation"("junit:junit:_")

    "androidTestImplementation"("androidx.test.ext:junit:_")
    "androidTestImplementation"("androidx.test.espresso:espresso-core:_")
}

plugins {
    id("com.android.application")
    id("com.github.pgreze.kotlinci.android-app")
}

configure<com.android.build.gradle.AppExtension> {
    defaultConfig {
        setApplicationId("com.github.pgreze.kotlinci")
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":utilities"))

    implementation("androidx.core:core-ktx:_")
    implementation("androidx.appcompat:appcompat:_")
    implementation("com.google.android.material:material:_")
    implementation("androidx.constraintlayout:constraintlayout:_")
    implementation("androidx.navigation:navigation-fragment-ktx:_")
    implementation("androidx.navigation:navigation-ui-ktx:_")
}

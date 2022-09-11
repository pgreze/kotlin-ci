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
    implementation(project(":domain"))

    implementation(AndroidX.core.ktx)
    implementation(AndroidX.appCompat)
    implementation(Google.android.material)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.navigation.fragmentKtx)
    implementation(AndroidX.navigation.uiKtx)
}

plugins {
    application
    kotlin("jvm")
}

application {
    mainClass.set("toolbox.AppKt")
}

dependencies {
    implementation(project(":core"))

    api(KotlinX.coroutines.core)
}

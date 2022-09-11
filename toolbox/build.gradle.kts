plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "com.github.pgreze.kotlinci"
version = project.findProperty("kotlinci_version")?.toString() ?: "WIP"

subprojects {
    plugins.withType(org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper::class) {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
            kotlinOptions.jvmTarget = "1.8"
        }

        tasks.withType<Test> {
            useJUnitPlatform()
            testLogging { events("passed", "skipped", "failed") }
        }

        dependencies {
            compileOnly(platform("org.jetbrains.kotlin:kotlin-bom:_"))
            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

            testImplementation(platform(Testing.junit.bom))
            testImplementation("org.junit.jupiter:junit-jupiter-api")
            testImplementation("org.junit.jupiter:junit-jupiter-params")
            testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
            testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
            testImplementation("com.google.truth:truth:_")
        }

        plugins.withType(MavenPublishPlugin::class) {
            configure<JavaPluginExtension> {
                withSourcesJar()
            }
            publishToolbox {
                from(components["java"])
            }
        }
    }
}

inline fun Project.publishToolbox(crossinline block: MavenPublication.() -> Unit) {
    publishing {
        publications {
            create<MavenPublication>("kotlinci") {
                groupId = rootProject.group.toString()
                artifactId = project.name
                version = rootProject.version.toString()

                block()

                pom {
                    name.set(project.name)
                    url.set("https://github.com/pgreze/kotlin-ci")
                }
            }
        }
//        repositories {
//            maven {
//                url = uri("https://TODO-github-packages")
//                credentials {
//                    username = System.getenv("GITHUB_PACKAGES_USERNAME")
//                        ?: project.findProperty("github_packages_username")?.toString()
//                    password = System.getenv("GITHUB_PACKAGES_PASSWORD")
//                        ?: project.findProperty("github_packages_password")?.toString()
//                }
//            }
//        }
    }
}
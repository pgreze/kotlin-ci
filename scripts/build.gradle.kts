plugins {
    kotlin("jvm")
    // id("org.jetbrains.kotlin.jvm") version "1.7.10"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.test {
    useJUnitPlatform()
    testLogging { events("passed", "skipped", "failed") }
}

val versions = org.jetbrains.kotlin.konan.properties.loadProperties(
    projectDir.resolve("../versions.properties").toString()
)
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    val junit5Version = versions["version.junit.jupiter"]
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit5Version")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit5Version")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junit5Version")
    testImplementation("com.google.truth:truth:${versions["version.com.google.truth..truth"]}")
}

fun newJavaTask(
    name: String,
    mainClass: String,
    group: String = "Scripts",
    requiresStdin: Boolean = false,
    block: JavaExec.() -> Unit = {}
): JavaExec = task(name, type = JavaExec::class) {
    this.group = group
    classpath = sourceSets["main"].runtimeClasspath
    this.mainClass.set(mainClass)
    if (requiresStdin) standardInput = System.`in`
    block()
}

//
// Interactive scripts
//

// TODO module generator

//
// RefreshVersions tasks
//

val versionsFile = projectDir.parentFile.resolve("versions.properties")

val backupVersions by tasks.register<Copy>("backupVersions") {
    group = "Scripts"
    from(versionsFile)
    into(buildDir.resolve("versions"))
}

val stripAvailableVersions = newJavaTask(
    name = "stripAvailableVersions",
    mainClass = "com.github.pgreze.versions.VersionsStripperKt"
) { args = listOf(versionsFile.absolutePath) }

newJavaTask(
    name = "resolveCandidateVersions",
    mainClass = "com.github.pgreze.versions.CandidateResolverKt"
) { args = listOf(versionsFile.absolutePath) }

package com.github.pgreze.versions

sealed class VersionToken
data class Artifact(val key: String, val version: String) : VersionToken()
class AvailableVersion(val version: String) : VersionToken() {
    val isSnapshot: Boolean
        get() = version.endsWith("-SNAPSHOT")
}

private val ARTIFACT_REGEX = "((?:version|plugin)\\.[^=]+)=([^=]+)".toRegex()
fun String.parseArtifactLine(): Artifact? =
    ARTIFACT_REGEX.matchEntire(this)?.destructured
        ?.let { (key, version) -> Artifact(key = key, version = version) }

private val AVAILABLE_VERSION_REGEX = "#[# ]+available=([^ ]+)".toRegex()
fun String.parseAvailableVersionLine(): AvailableVersion? =
    AVAILABLE_VERSION_REGEX.matchEntire(this)?.groupValues?.lastOrNull()
        ?.let { AvailableVersion(version = it) }

package com.github.pgreze.versions

import java.io.File
import kotlin.math.roundToInt

fun main(args: Array<String>) {
    val versions = File(args.first()).parseVersions()
    val artifactToAvailableVersion: CandidateResolution = versions.groupByCandidateAvailable()

    println(
        ">> Up-to-date artifacts:\n" + artifactToAvailableVersion.upToDate.joinToString("") {
            "- ${it.key} = ${it.version}\n"
        }
    )

    println(
        ">> With an update:\n" + artifactToAvailableVersion.newUpdateAvailable.entries.joinToString("") {
            "- ${it.key.key} :: ${it.key.version} -> ${it.value}\n"
        }
    )

    println(artifactToAvailableVersion.toSummary())
}

data class CandidateResolution(
    val upToDate: List<Artifact>,
    val newUpdateAvailable: Map<Artifact, String>,
    val upToDateRatio: Int = (upToDate.size + newUpdateAvailable.size).let { total ->
        if (total == 0) 100 else (100.0 * upToDate.size / total).roundToInt()
    },
)

fun CandidateResolution.toSummary(): String =
    "${upToDate.size} up-to-date artifacts ($upToDateRatio%) + ${newUpdateAvailable.size} with an update available"

private val String.isUnstable: Boolean
    get() = contains("(alpha|beta|rc)".toRegex(RegexOption.IGNORE_CASE))

fun ArtifactToAvailableVersions.groupByCandidateAvailable(): CandidateResolution {
    val keyToCandidate: List<Pair<Artifact, String?>> = map { (artifact, versions) ->
        val unstable = artifact.version.isUnstable
        val candidate = versions.lastOrNull { unstable || it.isUnstable.not() }
        artifact to candidate
    }
    val groups: Map<Boolean, List<Pair<Artifact, String?>>> = keyToCandidate.groupBy { it.second != null }

    val upToDate = groups.getOrElse(false, ::listOf).map { a -> a.first }
    return CandidateResolution(
        upToDate = upToDate,
        newUpdateAvailable = groups.getOrElse(true, ::listOf).map { it.first to it.second!! }.toMap(),
    )
}

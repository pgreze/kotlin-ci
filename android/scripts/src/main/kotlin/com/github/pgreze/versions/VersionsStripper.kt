package com.github.pgreze.versions

import kotlinx.coroutines.runBlocking
import java.io.File

fun main(args: Array<String>): Unit = runBlocking {
    val versions = File(args.first())
    val lines = versions.readLines().stripVersionsAvailableForProtoOnly()
    versions.writeText(lines.joinToString("\n") + "\n")
}

suspend fun List<String>.stripVersionsAvailableForProtoOnly(
    maxVersions: Int = 20,
): List<String> = stripVersionAvailableLines(
    maxVersions = maxVersions,
    artifactFilter = "version.com.(mercari|merpay).+proto.+".toRegex(),
)

suspend fun List<String>.stripVersionAvailableLines(
    maxVersions: Int,
    artifactFilter: Regex?,
): List<String> = sequence {
    var artifactLine: String? = null
    val versions = ArrayDeque<String>(initialCapacity = maxVersions)

    val emitAll: suspend SequenceScope<String>.() -> Unit = {
        artifactLine?.let { yield(it) }
        artifactLine = null
        yieldAll(versions)
        versions.clear()
    }

    for (line in this@stripVersionAvailableLines) {
        val availableVersion = line.parseAvailableVersionLine()
        if (availableVersion?.isSnapshot == true) {
            continue // Ignore SNAPSHOT versions, whatever the artifact.
        } else if (availableVersion != null && artifactLine != null) {
            if (versions.size == maxVersions) {
                versions.removeFirst()
            }
            versions.addLast(line)
        } else {
            emitAll()
            if (line.parseArtifactLine() != null && artifactFilter?.let(line::matches) != false) {
                artifactLine = line
            } else {
                yield(line)
            }
        }
    }
    // Flush the last artifact.
    emitAll()
}.toList()

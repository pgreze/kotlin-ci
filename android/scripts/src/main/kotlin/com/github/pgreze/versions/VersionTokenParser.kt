package com.github.pgreze.versions

import java.io.File

typealias ArtifactToAvailableVersions = Map<Artifact, List<String>>

fun File.parseVersions(): ArtifactToAvailableVersions =
    readLines().parseVersions()

fun List<String>.parseVersions(): ArtifactToAvailableVersions =
    mapNotNull { s ->
        s.parseArtifactLine() ?: s.parseAvailableVersionLine()?.takeUnless(AvailableVersion::isSnapshot)
    }.fold(mutableListOf<Pair<Artifact, MutableList<String>>>()) { acc, token ->
        when (token) {
            is Artifact -> acc.add(token to mutableListOf())
            is AvailableVersion -> acc.last().second.add(token.version)
        }
        acc
    }.toMap()

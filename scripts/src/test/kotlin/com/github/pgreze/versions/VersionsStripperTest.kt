package com.github.pgreze.versions

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class VersionsStripperTest {

    @Test
    fun `strip after XX versions`(): Unit = runBlocking {
        val lines = VERSIONS_LONG.split("\n")
            .stripVersionAvailableLines(maxVersions = 3, artifactFilter = null)
            .joinToString("\n").trim()
        assertThat(lines).isEqualTo(VERSIONS_SHORT_3.trim())
    }

    @Test
    fun `filter only specific artifacts`(): Unit = runBlocking {
        val lines = VERSIONS_LONG.split("\n")
            .stripVersionsAvailableForProtoOnly(maxVersions = 3)
            .joinToString("\n").trim()
        assertThat(lines).isEqualTo(VERSIONS_ONLY_PROTO_3.trim())
    }
}

private const val HEADERS = """
#### Dependencies and Plugin versions with their available updates.
#### Generated by `./gradlew refreshVersions` version 0.10.0
####
#### Don't manually edit or split the comments that start with four hashtags (####),
#### they will be overwritten by refreshVersions.
####
#### suppress inspection "SpellCheckingInspection" for whole file
#### suppress inspection "UnusedProperty" for whole file
"""

private const val VERSIONS_LONG = """
$HEADERS
# When updating, update also app_groundup/lint-baseline.xml
plugin.android=7.0.0-alpha15
## # available=7.0.0-beta01
## # available=7.1.0-alpha01

version.androidx.annotation=1.1.0
##              # available=1.2.0-alpha01
##              # available=1.2.0-beta01
##              # available=1.2.0-rc01
##              # available=1.2.0
##              # available=1.3.0-alpha01

version.androidx.appcompat=1.3.0-rc01
##             # available=1.3.0
##             # available=1.4.0-alpha01

version.androidx.constraintlayout-compose=1.0.0-alpha06
##                            # available=1.0.0-alpha07

version.com.mercari.proto..common.gateway=0.200.0
##                            # available=0.201.0-SNAPSHOT
##                            # available=0.201.0
##                            # available=0.202.0
##                            # available=0.203.0
##                            # available=0.204.0
##                            # available=0.205.0
##                            # available=0.206.0
##                            # available=0.207.0

version.com.google.code.findbugs..jsr305=3.0.2
"""

private const val VERSIONS_SHORT_3 = """
$HEADERS
# When updating, update also app_groundup/lint-baseline.xml
plugin.android=7.0.0-alpha15
## # available=7.0.0-beta01
## # available=7.1.0-alpha01

version.androidx.annotation=1.1.0
##              # available=1.2.0-rc01
##              # available=1.2.0
##              # available=1.3.0-alpha01

version.androidx.appcompat=1.3.0-rc01
##             # available=1.3.0
##             # available=1.4.0-alpha01

version.androidx.constraintlayout-compose=1.0.0-alpha06
##                            # available=1.0.0-alpha07

version.com.mercari.proto..common.gateway=0.200.0
##                            # available=0.205.0
##                            # available=0.206.0
##                            # available=0.207.0

version.com.google.code.findbugs..jsr305=3.0.2
"""

private const val VERSIONS_ONLY_PROTO_3 = """
$HEADERS
# When updating, update also app_groundup/lint-baseline.xml
plugin.android=7.0.0-alpha15
## # available=7.0.0-beta01
## # available=7.1.0-alpha01

version.androidx.annotation=1.1.0
##              # available=1.2.0-alpha01
##              # available=1.2.0-beta01
##              # available=1.2.0-rc01
##              # available=1.2.0
##              # available=1.3.0-alpha01

version.androidx.appcompat=1.3.0-rc01
##             # available=1.3.0
##             # available=1.4.0-alpha01

version.androidx.constraintlayout-compose=1.0.0-alpha06
##                            # available=1.0.0-alpha07

version.com.mercari.proto..common.gateway=0.200.0
##                            # available=0.205.0
##                            # available=0.206.0
##                            # available=0.207.0

version.com.google.code.findbugs..jsr305=3.0.2
"""
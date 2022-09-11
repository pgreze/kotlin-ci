package com.github.pgreze.versions

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class VersionTokenTest {

    @Nested
    inner class Artifact {
        @Test
        fun `parseArtifact with invalid line`() {
            assertThat("".parseArtifactLine()).isNull()
        }

        @Test
        fun `parseArtifact with a valid line`() {
            assertThat("version.androidx.compose.material=1.0.0-beta05".parseArtifactLine())
                .isEqualTo(Artifact(key = "version.androidx.compose.material", version = "1.0.0-beta05"))
        }
    }

    @Nested
    inner class VersionAvailable {
        @Test
        fun `parseAvailableVersionLine with invalid line`() {
            assertThat("".parseAvailableVersionLine()).isNull()
        }

        @Test
        fun `parseAvailableVersionLine with a valid line`() {
            assertThat("##          # available=1.1.0-alpha04".parseAvailableVersionLine()?.version)
                .isEqualTo("1.1.0-alpha04")
        }

        @Test
        fun `isSnapshot with non snapshots`() {
            assertFalse(AvailableVersion(version = "1.1.0").isSnapshot)
            assertFalse(AvailableVersion(version = "1.1.0-alpha04").isSnapshot)
        }

        @Test
        fun `isSnapshot with a snapshot`() {
            assertTrue(AvailableVersion(version = "1.1.0-alpha04-SNAPSHOT").isSnapshot)
        }
    }
}

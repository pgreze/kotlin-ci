package com.github.pgreze.kotlinci.core

import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class CollectionsTest {

    private companion object {
        private val ints = listOf(1, 2, 3, 4)
        private val strings = listOf("bonjour", "le", "monde")
    }

    @Test
    fun firstNotNull() {
        assertThat(ints.firstNotNull { if (it > 1) it else null })
            .isEqualTo(2)
    }

    @Test
    fun lastNotNull() {
        assertThat(ints.lastNotNull { if (it < 4) it else null })
            .isEqualTo(3)
    }

    @Test
    fun indexOfFirst() {
        assertThat(ints.indexOf { it == 2 })
            .isEqualTo(1)
    }

    @Test
    fun replaceLines() {
        assertThat(strings.replaceLines(1..1, listOf("hello", "world")))
            .isEqualTo(listOf("bonjour", "hello", "world", "monde"))
    }

    @Test
    fun insertWithAlphabeticOrder() {
        val expected = listOf("bonjour", "cordialement,", "le", "monde")

        assertThat(strings.insertWithAlphabeticOrder("cordialement", isInCollection = false))
            .isEqualTo(expected.map { it.trimEnd(',') })

        assertThat(strings.insertWithAlphabeticOrder("cordialement", isInCollection = true))
            .isEqualTo(expected)
    }

    @Test
    fun replaceLine() {
        assertThat(strings.insertLine(2, "beau"))
            .isEqualTo(listOf("bonjour", "le", "beau", "monde"))
    }
}

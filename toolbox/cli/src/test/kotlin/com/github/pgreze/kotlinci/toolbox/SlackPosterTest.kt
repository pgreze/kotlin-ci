package com.github.pgreze.kotlinci.toolbox

import kotlin.test.Test
import kotlin.test.assertTrue

class SlackPosterTest {

    @Test(expected = NullPointerException::class)
    fun appHasAGreeting() {
        assertTrue(postToSlack("Hello from test"))
    }
}

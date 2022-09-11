package com.github.pgreze.kotlinci.toolbox

import kotlin.test.Test
import kotlin.test.assertTrue

class SlackPosterTest {
    @Test fun appHasAGreeting() {
        assertTrue(postToSlack("Hello from test"))
    }
}

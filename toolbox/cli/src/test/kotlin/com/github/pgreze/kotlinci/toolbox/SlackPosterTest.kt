package com.github.pgreze.kotlinci.toolbox

import kotlin.test.Test

class SlackPosterTest {

    @Test(expected = NullPointerException::class)
    fun appHasAGreeting() {
        postToSlack("Hello from test")
    }
}

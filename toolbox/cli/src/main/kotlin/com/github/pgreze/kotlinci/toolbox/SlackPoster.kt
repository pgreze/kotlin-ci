package com.github.pgreze.kotlinci.toolbox

import com.github.pgreze.kotlinci.core.requireSlackToken

fun main() {
    postToSlack("こんにちは")
}

fun postToSlack(msg: String) {
    requireSlackToken()
    println("Message to slack: $msg")
}

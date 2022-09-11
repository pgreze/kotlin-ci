package com.github.pgreze.kotlinci.toolbox

fun main() {
    postToSlack("こんにちは")
}

fun postToSlack(msg: String): Boolean {
    println("Message to slack: $msg")
    return true
}

package com.github.pgreze.kotlinci.app

import com.github.pgreze.kotlinci.utilities.StringUtils

fun main() {
    val tokens = StringUtils.split(MessageUtils.getMessage())
    val result = StringUtils.join(tokens)
    println(result)
}

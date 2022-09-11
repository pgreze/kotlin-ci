package com.github.pgreze.kotlinci.core

fun <T, R> List<T>.firstNotNull(predicate: (T) -> R?): R? {
    for (i in indices) {
        return predicate(get(i)) ?: continue
    }
    return null
}

fun <T, R> List<T>.lastNotNull(predicate: (T) -> R?): R? {
    for (i in size - 1 downTo 0) {
        return predicate(get(i)) ?: continue
    }
    return null
}

@Throws(NoSuchElementException::class)
fun <T> List<T>.indexOf(predicate: (T) -> Boolean): Int =
    indexOfFirst(predicate).takeUnless { it == -1 } ?: throw NoSuchElementException()

fun List<String>.replaceLines(oldContentPosition: IntRange, lines: List<String>): List<String> =
    subList(0, oldContentPosition.first) + lines + drop(oldContentPosition.last + 1)

fun List<String>.insertWithAlphabeticOrder(
    /** The line to insert */
    line: String,
    /** Ensures a comma is added at the end of all each lines, except the last one. */
    isInCollection: Boolean = false
): List<String> {
    val newIndex = indexOfFirst { it > line }
    return if (newIndex == -1) {
        // Just append the new include at the end
        this.map { if (!it.endsWith(",") && isInCollection) "$it," else it } + listOf(line)
    } else {
        // Insert it before the next include
        insertLine(newIndex, line + if (isInCollection) "," else "")
    }
}

fun List<String>.insertLine(index: Int, line: String): List<String> =
    subList(0, index) + listOf(line) + drop(index)

package com.github.pgreze.kotlinci.core

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

@Suppress("UNCHECKED_CAST")
suspend fun <A, B> awaitPair(
    deferredA: Deferred<A>,
    deferredB: Deferred<B>,
): Pair<A, B> = listOf(deferredA, deferredB)
    .awaitAll()
    .let { Pair(it[0] as A, it[1] as B) }

@Suppress("UNCHECKED_CAST")
suspend fun <A, B, C> awaitTriple(
    deferredA: Deferred<A>,
    deferredB: Deferred<B>,
    deferredC: Deferred<C>,
): Triple<A, B, C> = listOf(deferredA, deferredB, deferredC)
    .awaitAll()
    .let { Triple(it[0] as A, it[1] as B, it[2] as C) }

suspend fun <T, R> Iterable<T>.asyncMap(
    context: CoroutineContext,
    transform: suspend (T) -> R
): List<Deferred<R>> = withContext(context) {
    map { async { transform(it) } }
}

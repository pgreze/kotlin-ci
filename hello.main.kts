@file:Repository("https://repo.maven.apache.org/maven2/")
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
@file:DependsOn("/Users/pgreze/.m2/repository/com/github/pgreze/kotlinci/core/WIP/core-WIP.jar")

import com.github.pgreze.kotlinci.core.awaitPair
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

runBlocking {
    val results = awaitPair(
        async { "Result1" },
        async { "Result2" },
    )
    println("Results: $results")
}

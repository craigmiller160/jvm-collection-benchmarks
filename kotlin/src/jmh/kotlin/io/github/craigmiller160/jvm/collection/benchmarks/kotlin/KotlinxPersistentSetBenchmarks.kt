package io.github.craigmiller160.jvm.collection.benchmarks.kotlin

import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.toPersistentSet
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

open class KotlinxPersistentSetBenchmarks {
    @State(Scope.Benchmark)
    open class KotlinSetState {
        val SIZE: Int = System.getenv("COLLECTION_SIZE").toInt()

        lateinit var SET: PersistentSet<String>
        lateinit var MORE_RECORDS: PersistentSet<String>

        @Setup(Level.Invocation)
        fun setUp() {
            SET = (0 until SIZE).map { "$it" }.toPersistentSet()
            MORE_RECORDS = (0 until 100).map { "more_$it" }.toPersistentSet()
        }
    }

    private fun validateState(state: KotlinSetState) {
        if (state.SET.size != state.SIZE) {
            throw Error("State has invalid value: ${state.SET.size}")
        }
    }

    @Benchmark
    fun add1(state: KotlinSetState): PersistentSet<String> {
        validateState(state)
        return state.SET.add("Hello")
    }

    @Benchmark
    fun add100(state: KotlinSetState): PersistentSet<String> {
        validateState(state)
        return state.SET.addAll(state.MORE_RECORDS)
    }

    @Benchmark
    fun remove1(state: KotlinSetState): PersistentSet<String> {
        validateState(state)
        return state.SET.remove("1000")
    }
}
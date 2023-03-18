package io.github.craigmiller160.jvm.collection.benchmarks.kotlin

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

class KotlinMutableSetBenchmarks {
    @State(Scope.Benchmark)
    class KotlinMutableSetState {
        val SIZE: Int = System.getenv("COLLECTION_SIZE").toInt()

        lateinit var SET: MutableSet<String>
        lateinit var MORE_RECORDS: MutableSet<String>

        @Setup(Level.Invocation)
        fun setUp() {
            SET = (0 until SIZE).map { "$it" }.toMutableSet()
            MORE_RECORDS = (0 until 100).map { "more_$it" }.toMutableSet()
        }
    }

    private fun validateState(state: KotlinMutableSetState) {
        if (state.SET.size != state.SIZE) {
            throw Error("State has invalid value: ${state.SET.size}")
        }
    }

    @Benchmark
    fun add1(state: KotlinMutableSetState): Set<String> {
        validateState(state)
        state.SET += "Hello"
        return state.SET
    }

    @Benchmark
    fun add100(state: KotlinMutableSetState): Set<String> {
        validateState(state)
        state.SET += state.MORE_RECORDS
        return state.SET
    }

    @Benchmark
    fun remove1(state: KotlinMutableSetState): Set<String> {
        validateState(state)
        state.SET.remove("1000")
        return state.SET
    }
}
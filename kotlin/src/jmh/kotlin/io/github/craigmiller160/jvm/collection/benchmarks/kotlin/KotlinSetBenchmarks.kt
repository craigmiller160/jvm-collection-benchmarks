package io.github.craigmiller160.jvm.collection.benchmarks.kotlin

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

open class KotlinSetBenchmarks {
    @State(Scope.Benchmark)
    open class KotlinSetState {
        val SIZE: Int = System.getenv("COLLECTION_SIZE").toInt()

        lateinit var SET: Set<String>
        lateinit var MORE_RECORDS: Set<String>

        @Setup(Level.Invocation)
        fun setUp() {
            SET = (0 until SIZE).map { "$it" }.toSet()
            MORE_RECORDS = (0 until 100).map { "more_$it" }.toSet()
        }
    }

    private fun validateState(state: KotlinSetState) {
        if (state.SET.size != state.SIZE) {
            throw Error("State has invalid value: ${state.SET.size}")
        }
    }

    @Benchmark
    fun add1(state: KotlinSetState): Set<String> {
        validateState(state)
        return state.SET + "Hello"
    }

    @Benchmark
    fun add100(state: KotlinSetState): Set<String> {
        validateState(state)
        return state.SET + state.MORE_RECORDS
    }

    @Benchmark
    fun remove1(state: KotlinSetState): Set<String> {
        validateState(state)
        return state.SET.filter { it != "1000" }.toSet()
    }
}
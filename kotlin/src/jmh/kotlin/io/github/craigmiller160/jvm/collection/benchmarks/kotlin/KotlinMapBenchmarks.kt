package io.github.craigmiller160.jvm.collection.benchmarks.kotlin

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

class KotlinMapBenchmarks {
    @State(Scope.Benchmark)
    class KotlinMapState {
        val SIZE: Int = System.getenv("COLLECTION_SIZE").toInt()

        lateinit var MAP: Map<String,String>
        lateinit var MORE_RECORDS: Map<String,String>

        @Setup(Level.Invocation)
        fun setUp() {
            MAP = (0 until SIZE).map { "$it" to "$it" }.toMap()
            MORE_RECORDS = (0 until 100).map { "more_$it" to "more_$it" }.toMap()
        }
    }

    private fun validateState(state: KotlinMapState) {
        if (state.MAP.size != state.SIZE) {
            throw Error("State has invalid value: ${state.MAP.size}")
        }
    }

    @Benchmark
    fun add1(state: KotlinMapState): Map<String,String> {
        validateState(state)
        return state.MAP + ("Hello" to "Hello")
    }

    @Benchmark
    fun add100(state: KotlinMapState): Map<String,String> {
        validateState(state)
        return state.MAP + state.MORE_RECORDS
    }

    @Benchmark
    fun remove1(state: KotlinMapState): Map<String,String> {
        validateState(state)
        return state.MAP.filter { (key) -> key != "1000" }
    }
}
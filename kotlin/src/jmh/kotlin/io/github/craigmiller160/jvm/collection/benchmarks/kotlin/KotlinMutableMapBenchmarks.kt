package io.github.craigmiller160.jvm.collection.benchmarks.kotlin

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

class KotlinMutableMapBenchmarks {
    @State(Scope.Benchmark)
    class KotlinMutableMapState {
        val SIZE: Int = System.getenv("COLLECTION_SIZE").toInt()

        lateinit var MAP: MutableMap<String,String>
        lateinit var MORE_RECORDS: MutableMap<String,String>

        @Setup(Level.Invocation)
        fun setUp() {
            MAP = (0 until SIZE).associate { "$it" to "$it" }.toMutableMap()
            MORE_RECORDS = (0 until 100).associate { "more_$it" to "more_$it" }.toMutableMap()
        }
    }

    private fun validateState(state: KotlinMutableMapState) {
        if (state.MAP.size != state.SIZE) {
            throw Error("State has invalid value: ${state.MAP.size}")
        }
    }

    @Benchmark
    fun add1(state: KotlinMutableMapState): Map<String,String> {
        validateState(state)
        state.MAP += ("Hello" to "Hello")
        return state.MAP
    }

    @Benchmark
    fun add100(state: KotlinMutableMapState): Map<String,String> {
        validateState(state)
        state.MAP += state.MORE_RECORDS
        return state.MAP
    }

    @Benchmark
    fun remove1(state: KotlinMutableMapState): Map<String,String> {
        validateState(state)
        state.MAP.remove("1000")
        return state.MAP
    }
}
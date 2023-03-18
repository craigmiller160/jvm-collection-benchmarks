package io.github.craigmiller160.jvm.collection.benchmarks.kotlin

import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.toPersistentMap
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

class KotlinxPersistentMapBenchmarks {
    @State(Scope.Benchmark)
    class KotlinMapState {
        val SIZE: Int = System.getenv("COLLECTION_SIZE").toInt()

        lateinit var MAP: PersistentMap<String,String>
        lateinit var MORE_RECORDS: PersistentMap<String, String>

        @Setup(Level.Invocation)
        fun setUp() {
            MAP = (0 until SIZE).associate { "$it" to "$it" }.toPersistentMap()
            MORE_RECORDS = (0 until 100).associate { "more_$it" to "more_$it" }.toPersistentMap()
        }
    }

    private fun validateState(state: KotlinMapState) {
        if (state.MAP.size != state.SIZE) {
            throw Error("State has invalid value: ${state.MAP.size}")
        }
    }

    @Benchmark
    fun add1(state: KotlinMapState): PersistentMap<String,String> {
        validateState(state)
        return state.MAP.put("Hello", "Hello")
    }

    @Benchmark
    fun add100(state: KotlinMapState): PersistentMap<String,String> {
        validateState(state)
        return state.MAP.putAll(state.MORE_RECORDS)
    }

    @Benchmark
    fun remove1(state: KotlinMapState): PersistentMap<String,String> {
        validateState(state)
        return state.MAP.remove("1000")
    }
}
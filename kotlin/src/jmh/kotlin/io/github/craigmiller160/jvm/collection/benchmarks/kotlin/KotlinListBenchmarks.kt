package io.github.craigmiller160.jvm.collection.benchmarks.kotlin

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

open class KotlinListBenchmarks {
    @State(Scope.Benchmark)
    open class KotlinListState {
        val SIZE: Int = System.getenv("COLLECTION_SIZE").toInt()

        lateinit var LIST: List<String>
        lateinit var MORE_RECORDS: List<String>

        @Setup(Level.Invocation)
        fun setUp() {
            LIST = (0 until SIZE).map { "$it" }
            MORE_RECORDS = (0 until 100).map { "more_$it" }
        }
    }

    private fun validateState(state: KotlinListState) {
        if (state.LIST.size != state.SIZE) {
            throw Error("State has invalid value: ${state.LIST.size}")
        }
    }

    @Benchmark
    fun append1(state: KotlinListState): List<String> {
        validateState(state)
        return state.LIST + "Hello"
    }

    @Benchmark
    fun append100(state: KotlinListState): List<String> {
        validateState(state)
        return state.LIST + state.MORE_RECORDS
    }

    @Benchmark
    fun prepend1(state: KotlinListState): List<String> {
        validateState(state)
        return listOf("Hello") + state.LIST
    }

    @Benchmark
    fun prepend100(state: KotlinListState): List<String> {
        validateState(state)
        return state.MORE_RECORDS + state.LIST
    }

    @Benchmark
    fun remove1AtEnd(state: KotlinListState): List<String> {
        validateState(state)
        return state.LIST.slice(0 until state.SIZE)
    }

    @Benchmark
    fun remove1AtStart(state: KotlinListState): List<String> {
        validateState(state)
        return state.LIST.slice(1 until state.SIZE)
    }
}
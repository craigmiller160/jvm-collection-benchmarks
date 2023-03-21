package io.github.craigmiller160.jvm.collection.benchmarks.kotlin

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

open class KotlinMutableListBenchmarks {
    @State(Scope.Benchmark)
    open class KotlinMutableListState {
        val SIZE: Int = System.getenv("COLLECTION_SIZE").toInt()

        lateinit var LIST: MutableList<String>
        lateinit var MORE_RECORDS: MutableList<String>

        @Setup(Level.Invocation)
        fun setUp() {
            LIST = (0 until SIZE).map { "$it" }.toMutableList()
            (LIST as ArrayList<String>).ensureCapacity(1_001_000)
            MORE_RECORDS = (0 until 100).map { "more_$it" }.toMutableList()
        }
    }

    private fun validateState(state: KotlinMutableListState) {
        if (state.LIST.size != state.SIZE) {
            throw Error("State has invalid value: ${state.LIST.size}")
        }
    }

    @Benchmark
    fun append1(state: KotlinMutableListState): List<String> {
        validateState(state)
        state.LIST += "Hello"
        return state.LIST
    }

    @Benchmark
    fun append100(state: KotlinMutableListState): List<String> {
        validateState(state)
        state.LIST += state.MORE_RECORDS
        return state.LIST
    }

    @Benchmark
    fun prepend1(state: KotlinMutableListState): List<String> {
        validateState(state)
        state.LIST.add(0, "Hello")
        return state.LIST
    }

    @Benchmark
    fun prepend100(state: KotlinMutableListState): List<String> {
        validateState(state)
        state.LIST.addAll(0, state.MORE_RECORDS)
        return state.LIST
    }

    @Benchmark
    fun remove1AtEnd(state: KotlinMutableListState): List<String> {
        validateState(state)
        state.LIST.removeAt(state.SIZE - 1)
        return state.LIST
    }

    @Benchmark
    fun remove1AtStart(state: KotlinMutableListState): List<String> {
        validateState(state)
        state.LIST.removeAt(0)
        return state.LIST
    }
}
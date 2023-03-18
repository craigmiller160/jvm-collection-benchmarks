package io.github.craigmiller160.jvm.collection.benchmarks.kotlin

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

class KotlinxPersistentListBenchmarks {
    @State(Scope.Benchmark)
    class KotlinListState {
        val SIZE: Int = System.getenv("COLLECTION_SIZE").toInt()

        lateinit var LIST: PersistentList<String>
        lateinit var MORE_RECORDS: PersistentList<String>

        @Setup(Level.Invocation)
        fun setUp() {
            LIST = (0 until SIZE).map { "$it" }.toPersistentList()
            MORE_RECORDS = (0 until 100).map { "more_$it" }.toPersistentList()
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
        return state.LIST.add("Hello")
    }

    @Benchmark
    fun append100(state: KotlinListState): List<String> {
        validateState(state)
        return state.LIST.addAll(state.MORE_RECORDS)
    }

    @Benchmark
    fun prepend1(state: KotlinListState): List<String> {
        validateState(state)
        return state.LIST.add(0, "Hello")
    }

    @Benchmark
    fun prepend100(state: KotlinListState): List<String> {
        validateState(state)
        return state.LIST.addAll(0, state.MORE_RECORDS)
    }

    @Benchmark
    fun remove1AtEnd(state: KotlinListState): List<String> {
        validateState(state)
        return state.LIST.removeAt(state.SIZE - 1)
    }

    @Benchmark
    fun remove1AtStart(state: KotlinListState): List<String> {
        validateState(state)
        return state.LIST.removeAt(0)
    }
}
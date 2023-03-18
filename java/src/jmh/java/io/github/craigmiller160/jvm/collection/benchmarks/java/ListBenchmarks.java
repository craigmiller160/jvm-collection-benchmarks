package io.github.craigmiller160.jvm.collection.benchmarks.java;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.List;

public class ListBenchmarks {

    @State(Scope.Benchmark)
    public static class ArrayListState {
        public static final int SIZE = Integer.parseInt(System.getenv("COLLECTION_SIZE"));
        public List<String> LIST;
        public List<String> MORE_RECORDS;

        @Setup(Level.Invocation)
        public void setUp() {
            LIST = new ArrayList<>();
            for (int i = 0; i < SIZE; i++) {
                LIST.add(String.valueOf(i));
            }

            MORE_RECORDS = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                MORE_RECORDS.add(String.valueOf(i));
            }
        }
    }

    private void validateState(final ArrayListState state) {
        if (state.LIST.size() != ArrayListState.SIZE) {
            throw new Error("State has invalid value: %d".formatted(state.LIST.size()));
        }
    }

    @Benchmark
    public List<String> listAppend1(final ArrayListState state) {
        validateState(state);
        state.LIST.add("Hello");
        return state.LIST;
    }

    @Benchmark
    public List<String> listAppend100(final ArrayListState state) {
        validateState(state);
        state.LIST.addAll(state.MORE_RECORDS);
        return state.LIST;
    }

    @Benchmark
    public List<String> listRemove1AtEnd(final ArrayListState state) {
        validateState(state);
        state.LIST.remove(ArrayListState.SIZE - 1);
        return state.LIST;
    }

    @Benchmark
    public List<String> listRemove1AtStart(final ArrayListState state) {
        validateState(state);
        state.LIST.remove(0);
        return state.LIST;
    }
}

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

        @Setup(Level.Invocation)
        public void setUp() {
            LIST = new ArrayList<>();
            for (int i = 0; i < SIZE; i++) {
                LIST.add(String.valueOf(i));
            }
        }
    }



    @Benchmark
    public List<String> listAdd(final ArrayListState state) {
        if (state.LIST.size() != ArrayListState.SIZE) {
            throw new Error("State has invalid value: %d".formatted(state.LIST.size()));
        }
        state.LIST.add("Hello");
        return state.LIST;
    }
}

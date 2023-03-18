package io.github.craigmiller160.jvm.collection.benchmarks.java;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaSetBenchmarks {
    @State(Scope.Benchmark)
    public static class HashSetState {
        public static final int SIZE = Integer.parseInt(System.getenv("COLLECTION_SIZE"));
        public Set<String> SET;
        public Set<String> MORE_RECORDS;
        
        @Setup(Level.Invocation)
        public void setup() {
            SET = new HashSet<>();
            for (int i = 0; i < SIZE; i++) {
                SET.add(String.valueOf(i));
            }
            
            MORE_RECORDS = new HashSet<>();
            for (int i = 0; i < 100; i++) {
                MORE_RECORDS.add("more_%d".formatted(i));
            }
        }
    }

    private void validateState(final HashSetState state) {
        if (state.SET.size() != HashSetState.SIZE) {
            throw new Error("State has invalid value: %d".formatted(state.SET.size()));
        }
    }

    @Benchmark
    public Set<String> add1(final HashSetState state) {
        validateState(state);
        state.SET.add("Hello");
        return state.SET;
    }

    @Benchmark
    public Set<String> add100(final HashSetState state) {
        validateState(state);
        state.SET.addAll(state.MORE_RECORDS);
        return state.SET;
    }

    @Benchmark
    public Set<String> remove1(final HashSetState state) {
        validateState(state);
        state.SET.remove("1000");
        return state.SET;
    }
}

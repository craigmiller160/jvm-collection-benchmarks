package io.github.craigmiller160.jvm.collection.benchmarks.java;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JavaMapBenchmarks {
    @State(Scope.Benchmark)
    public static class HashMapState {
        public static final int SIZE = Integer.parseInt(System.getenv("COLLECTION_SIZE"));
        public Map<String,String> MAP;
        public Map<String,String> MORE_RECORDS;

        @Setup(Level.Invocation)
        public void setup() {
            MAP = new HashMap<>();
            for (int i = 0; i < SIZE; i++) {
                MAP.put(String.valueOf(i), String.valueOf(i));
            }

            MORE_RECORDS = new HashMap<>();
            for (int i = 0; i < 100; i++) {
                MORE_RECORDS.put(
                        "more_%d".formatted(i),
                        "more_%d".formatted(i)
                );
            }
        }
    }

    private void validateState(final HashMapState state) {
        if (state.MAP.size() != HashMapState.SIZE) {
            throw new Error("State has invalid value: %d".formatted(state.MAP.size()));
        }
    }

    @Benchmark
    public Map<String,String> add1(final HashMapState state) {
        validateState(state);
        state.MAP.put("Hello", "Hello");
        return state.MAP;
    }

    @Benchmark
    public Map<String,String> add100(final HashMapState state) {
        validateState(state);
        state.MAP.putAll(state.MORE_RECORDS);
        return state.MAP;
    }

    @Benchmark
    public Map<String,String> remove1(final HashMapState state) {
        validateState(state);
        state.MAP.remove("1000");
        return state.MAP;
    }
}

package io.github.craigmiller160.jvm.collection.benchmarks.java;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.pcollections.HashTreePMap;
import org.pcollections.PMap;

public class PMapBenchmarks {
    @State(Scope.Benchmark)
    public static class PMapState {
        public static final int SIZE = Integer.parseInt(System.getenv("COLLECTION_SIZE"));
        public PMap<String,String> MAP;
        public PMap<String,String> MORE_RECORDS;

        @Setup(Level.Invocation)
        public void setup() {
            final java.util.Map<String,String> map = new java.util.HashMap<>();
            for (int i = 0; i < SIZE; i++) {
                map.put(String.valueOf(i), String.valueOf(i));
            }
            MAP = HashTreePMap.from(map);

            final java.util.Map<String,String> moreRecords = new java.util.HashMap<>();
            for (int i = 0; i < 100; i++) {
                moreRecords.put(
                        "more_%d".formatted(i),
                        "more_%d".formatted(i)
                );
            }
            MORE_RECORDS = HashTreePMap.from(moreRecords);
        }
    }

    private void validateState(final PMapState state) {
        if (state.MAP.size() != PMapState.SIZE) {
            throw new Error("State has invalid value: %d".formatted(state.MAP.size()));
        }
    }

    @Benchmark
    public PMap<String,String> add1(final PMapState state) {
        validateState(state);
        return state.MAP.plus("Hello", "Hello");
    }

    @Benchmark
    public PMap<String,String> add100(final PMapState state) {
        validateState(state);
        return state.MAP.plusAll(state.MORE_RECORDS);
    }

    @Benchmark
    public PMap<String,String> remove1(final PMapState state) {
        validateState(state);
        return state.MAP.minus("1000");
    }
}

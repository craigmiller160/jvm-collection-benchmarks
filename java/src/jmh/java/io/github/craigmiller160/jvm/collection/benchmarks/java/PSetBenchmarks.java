package io.github.craigmiller160.jvm.collection.benchmarks.java;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.pcollections.HashTreePSet;
import org.pcollections.PSet;

public class PSetBenchmarks {
    @State(Scope.Benchmark)
    public static class PSetState {
        public static final int SIZE = Integer.parseInt(System.getenv("COLLECTION_SIZE"));
        public PSet<String> SET;
        public PSet<String> MORE_RECORDS;

        @Setup(Level.Invocation)
        public void setup() {
            final java.util.Set<String> set = new java.util.HashSet<>();
            for (int i = 0; i < SIZE; i++) {
                set.add(String.valueOf(i));
            }
            SET = HashTreePSet.from(set);

            final java.util.Set<String> moreRecords = new java.util.HashSet<>();
            for (int i = 0; i < 100; i++) {
                moreRecords.add("more_%d".formatted(i));
            }
            MORE_RECORDS = HashTreePSet.from(moreRecords);
        }
    }

    private void validateState(final PSetState state) {
        if (state.SET.size() != PSetState.SIZE) {
            throw new Error("State has invalid value: %d".formatted(state.SET.size()));
        }
    }

    @Benchmark
    public PSet<String> add1(final PSetState state) {
        validateState(state);
        return state.SET.plus("Hello");
    }

    @Benchmark
    public PSet<String> add100(final PSetState state) {
        validateState(state);
        return state.SET.plusAll(state.MORE_RECORDS);
    }

    @Benchmark
    public PSet<String> remove1(final PSetState state) {
        validateState(state);
        return state.SET.minus("1000");
    }
}

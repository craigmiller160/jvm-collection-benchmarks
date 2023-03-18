package io.github.craigmiller160.jvm.collection.benchmarks.java;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;


public class VavrSetBenchmarks {
    @State(Scope.Benchmark)
    public static class VavrSetState {
        public static final int SIZE = Integer.parseInt(System.getenv("COLLECTION_SIZE"));
        public Set<String> SET;
        public Set<String> MORE_RECORDS;

        @Setup(Level.Invocation)
        public void setup() {
            final java.util.Set<String> set = new java.util.HashSet<>();
            for (int i = 0; i < SIZE; i++) {
                SET.add(String.valueOf(i));
            }
            SET = HashSet.ofAll(set);

            final java.util.Set<String> moreRecords = new java.util.HashSet<>();
            for (int i = 0; i < 100; i++) {
                moreRecords.add("more_%d".formatted(i));
            }
            MORE_RECORDS = HashSet.ofAll(moreRecords);
        }
    }

    private void validateState(final VavrSetState state) {
        if (state.SET.size() != VavrSetState.SIZE) {
            throw new Error("State has invalid value: %d".formatted(state.SET.size()));
        }
    }

    @Benchmark
    public Set<String> add1(final VavrSetState state) {
        validateState(state);
        return state.SET.add("Hello");
    }

    @Benchmark
    public Set<String> add100(final VavrSetState state) {
        validateState(state);
        return state.SET.addAll(state.MORE_RECORDS);
    }

    @Benchmark
    public Set<String> remove1(final VavrSetState state) {
        validateState(state);
        return state.SET.remove("1000");
    }
}

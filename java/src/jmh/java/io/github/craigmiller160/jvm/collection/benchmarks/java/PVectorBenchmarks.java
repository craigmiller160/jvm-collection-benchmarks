package io.github.craigmiller160.jvm.collection.benchmarks.java;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

import java.util.ArrayList;

public class PVectorBenchmarks {
    @State(Scope.Benchmark)
    public static class PVectorState {
        public static final int SIZE = Integer.parseInt(System.getenv("COLLECTION_SIZE"));
        public PVector<String> LIST;
        public PVector<String> MORE_RECORDS;

        @Setup(Level.Invocation)
        public void setUp() {
            final java.util.List<String> list = new ArrayList<>();
            for (int i = 0; i < SIZE; i++) {
                list.add(String.valueOf(i));
            }
            LIST = TreePVector.from(list);

            final java.util.List<String> moreRecords = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                moreRecords.add("more_%d".formatted(i));
            }
            MORE_RECORDS = TreePVector.from(moreRecords);
        }
    }

    private void validateState(final PVectorState state) {
        if (state.LIST.size() != PVectorState.SIZE) {
            throw new Error("State has invalid value: %d".formatted(state.LIST.size()));
        }
    }

    @Benchmark
    public PVector<String> append1(final PVectorState state) {
        validateState(state);
        return state.LIST.plus("Hello");
    }

    @Benchmark
    public PVector<String> append100(final PVectorState state) {
        validateState(state);
        return state.LIST.plusAll(state.MORE_RECORDS);
    }

    @Benchmark
    public PVector<String> prepend1(final PVectorState state) {
        validateState(state);
        return state.LIST.plus(0, "Hello");
    }

    @Benchmark
    public PVector<String> prepend100(final PVectorState state) {
        validateState(state);
        return state.LIST.plusAll(0, state.MORE_RECORDS);
    }

    @Benchmark
    public PVector<String> remove1AtEnd(final PVectorState state) {
        validateState(state);
        return state.LIST.minus(0);
    }

    @Benchmark
    public PVector<String> remove1AtStart(final PVectorState state) {
        validateState(state);
        return state.LIST.minus(PVectorState.SIZE - 1);
    }
}

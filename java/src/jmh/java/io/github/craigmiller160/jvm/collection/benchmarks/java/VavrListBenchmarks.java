package io.github.craigmiller160.jvm.collection.benchmarks.java;

import io.vavr.collection.List;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;

import java.util.ArrayList;

public class VavrListBenchmarks {
    public static class VavrListState {
        public static final int SIZE = Integer.parseInt(System.getenv("COLLECTION_SIZE"));
        public List<String> LIST;
        public List<String> MORE_RECORDS;

        @Setup(Level.Invocation)
        public void setUp() {
            final java.util.List<String> list = new ArrayList<>();
            for (int i = 0; i < SIZE; i++) {
                list.add(String.valueOf(i));
            }
            LIST = List.ofAll(list);

            final java.util.List<String> moreRecords = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                moreRecords.add("more_%d".formatted(i));
            }
            MORE_RECORDS = List.ofAll(moreRecords);
        }
    }

    private void validateState(final VavrListState state) {
        if (state.LIST.size() != VavrListState.SIZE) {
            throw new Error("State has invalid value: %d".formatted(state.LIST.size()));
        }
    }

    @Benchmark
    public List<String> append1(final VavrListState state) {
        validateState(state);
        return state.LIST.append("Hello");
    }

    @Benchmark
    public List<String> append100(final VavrListState state) {
        validateState(state);
        return state.LIST.appendAll(state.MORE_RECORDS);
    }

    @Benchmark
    public List<String> prepend1(final VavrListState state) {
        validateState(state);
        return state.LIST.prepend("Hello");
    }

    @Benchmark
    public List<String> prepend100(final VavrListState state) {
        validateState(state);
        return state.LIST.prependAll(state.MORE_RECORDS);
    }

    @Benchmark
    public List<String> remove1AtEnd(final VavrListState state) {
        validateState(state);
        return state.LIST.removeAt(0);
    }

    @Benchmark
    public List<String> remove1AtStart(final VavrListState state) {
        validateState(state);
        return state.LIST.removeAt(VavrListState.SIZE - 1);
    }
}

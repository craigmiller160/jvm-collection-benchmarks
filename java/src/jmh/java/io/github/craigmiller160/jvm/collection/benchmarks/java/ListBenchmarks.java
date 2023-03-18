package io.github.craigmiller160.jvm.collection.benchmarks.java;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.stream.IntStream;

public class ListBenchmarks {
    @State(Scope.Benchmark)
    public static class ArrayListState {
        public final List<String> LIST = IntStream.range(0, 1_000_000)
                .mapToObj(String::valueOf)
                .toList();
    }

    @Benchmark
    public void listAdd(final ArrayListState state,
                        final Blackhole blackhole) {
        if (state.LIST.size() != 1_000_000) {
            throw new RuntimeException("State has invalid value: %d".formatted(state.LIST.size()));
        }
        state.LIST.add("Hello");
        blackhole.consume(state);
    }
}

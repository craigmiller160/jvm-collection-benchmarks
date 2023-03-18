package io.github.craigmiller160.jvm.collection.benchmarks.java;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListBenchmarks {
    private static final int LIST_SIZE = 1_000_000;

    @State(Scope.Benchmark)
    public static class ArrayListState {
        public final List<String> LIST = IntStream.range(0, LIST_SIZE)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<String> listAdd(final ArrayListState state) {
        if (state.LIST.size() != LIST_SIZE) {
            throw new RuntimeException("State has invalid value: %d".formatted(state.LIST.size()));
        }
        state.LIST.add("Hello");
        return state.LIST;
    }
}

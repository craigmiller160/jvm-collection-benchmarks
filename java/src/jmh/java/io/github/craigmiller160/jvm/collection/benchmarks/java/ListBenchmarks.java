package io.github.craigmiller160.jvm.collection.benchmarks.java;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.List;

@State(Scope.Benchmark)
public class ListBenchmarks {
    private static final int LIST_SIZE = 1_000_000;

//    @State(Scope.Thread)
//    public static class ArrayListState {
//        public final List<String> LIST = IntStream.range(0, LIST_SIZE)
//                .mapToObj(String::valueOf)
//                .collect(Collectors.toList());
//    }

    private List<String> list;

    @Setup(Level.Invocation)
    public void setUp() {
        list = new ArrayList<>();
        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(String.valueOf(i));
        }
    }

    @Benchmark
    public List<String> listAdd() {
        if (list.size() != LIST_SIZE) {
            throw new Error("State has invalid value: %d".formatted(list.size()));
        }
        list.add("Hello");
        return list;
    }
}

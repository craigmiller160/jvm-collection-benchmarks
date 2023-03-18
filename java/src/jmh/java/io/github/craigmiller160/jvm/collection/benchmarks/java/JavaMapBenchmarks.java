package io.github.craigmiller160.jvm.collection.benchmarks.java;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.HashMap;
import java.util.Map;

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
}

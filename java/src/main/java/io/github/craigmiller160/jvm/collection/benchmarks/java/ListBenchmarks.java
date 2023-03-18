package io.github.craigmiller160.jvm.collection.benchmarks.java;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class ListBenchmarks {
    @Benchmark
    public void hello() {
        System.out.println("Hello World");
    }
}

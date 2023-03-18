package io.github.craigmiller160.jvm.collection.benchmarks.java;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
//@OutputTimeUnit(TimeUnit.NANOSECONDS)
//@BenchmarkMode(Mode.AverageTime)
public class ListBenchmarks {
    @Benchmark
    public void hello() {
//        System.out.println("Hello World");
        String a = "hello";
    }
}

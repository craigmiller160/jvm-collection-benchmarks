# JVM Collection Benchmarks

This project contains benchmarks of various collection options on the JVM. The motivation behind this is identifying the overall performance of solutions that support "immutable mutations", ie either copy-on-write or persistent data structures, and comparing that to "normal" mutable collections. Functional programming encourages immutable design patterns, but architectural purity must always be balanced against its cost.

A variety of "immutable mutation" solutions will be compared against mutable ones in this project, in the hopes of identifying the costs involved in each of them.

## Benchmarking Stats

| Stat                       | Value        |
|----------------------------|--------------|
| JDK                        | 19.0.2       |
| Build Tool                 | Gradle 8.0   |
| JMH Version                | 1.36         |
| Forks                      | 10           |
| Iterations Per Fork        | 10           |
| Warmup Iterations Per Fork | 2            |
| Batch Size                 | 1,000        | 
| Mode                       | Average Time |
| Time Unit                  | Nanoseconds  |
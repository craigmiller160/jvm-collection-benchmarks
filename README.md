# JVM Collection Benchmarks

This project contains benchmarks of various collection options on the JVM. The motivation behind this is identifying the overall performance of solutions that support "immutable mutations", ie either copy-on-write or persistent data structures, and comparing that to "normal" mutable collections. Functional programming encourages immutable design patterns, but architectural purity must always be balanced against its cost.

A variety of "immutable mutation" solutions will be compared against mutable ones in this project, in the hopes of identifying the costs involved in each of them.

## Benchmarking Settings

| Settings                             | Value        |
|--------------------------------------|--------------|
| JDK                                  | 19.0.2       |
| Build Tool                           | Gradle 8.0   |
| JMH Version                          | 1.36         |
| Forks                                | 5            |
| Iterations Per Fork                  | 10           |
| Warmup Iterations Per Fork           | 2            |
| Batch Size                           | 100          | 
| Mode                                 | Average Time |
| Time Unit                            | Nanoseconds  |
| Records in Collection Being Modified | 1,000,000    |

## How to Run

Use the `run.sh` script at the root of the project, passing in the name of the language-specific project directory as an argument. This ensures that the above benchmarking settings are consistently applied across all projects.

## Result Summary

This is a summary of the results from a round of tests run on my personal machine.

### Machine Stats
| Stat         | Value            |
|--------------|------------------|
| Manufacturer | Apple            |
| Model        | MacBook Pro 16"  |
| CPU          | M1 Pro (10 core) |
| RAM          | 32 GB            |

### List Tests

| Operation              | java.util.ArrayList | io.vavr.collection.List | kotlin.collection.List | kotlin.collection.MutableList | kotlinx.collections.immutable.PersistentList |
|------------------------|---------------------|-------------------------|------------------------|-------------------------------|----------------------------------------------|
| Append 1 item          | 0.162 ms/op         |                         |                        |                               |                                              |
| Append 100 items       | 0.250 ms/op         |                         |                        |                               |                                              |
| Prepend 1 item         | 40.690 ms/op        |                         |                        |                               |                                              |
| Prepend 100 items      | 39.568 ms/op        |                         |                        |                               |                                              |
| Remove 1 item at end   | 0.164 ms/op         |                         |                        |                               |                                              |
| Remove 1 item at start | 43.065 ms/op        |                         |                        |                               |                                              |

### Set Tests

| Operation     | java.util.HashSet | io.vavr.collection.HashSet | kotlin.collection.Set | kotlin.collection.MutableSet | kotlinx.collections.immutable.PersistentSet |
|---------------|-------------------|----------------------------|-----------------------|------------------------------|---------------------------------------------|
| Add 1 item    |                   |                            |                       |                              |                                             |
| Add 100 items |                   |                            |                       |                              |                                             |
| Remove 1 item |                   |                            |                       |                              |                                             |

### Map Tests

| Operation     | java.util.HashMap | io.vavr.collection.HashMap | kotlin.collection.Map | kotlin.collection.MutableMap | kotlinx.collections.immutable.PersistentMap |
|---------------|-------------------|----------------------------|-----------------------|------------------------------|---------------------------------------------|
| Add 1 item    | 0.236 ms/op       |                            |                       |                              |                                             |
| Add 100 items | 1.091 ms/op       |                            |                       |                              |                                             |
| Remove 1 item | 0.360 ms/op       |                            |                       |                              |                                             |
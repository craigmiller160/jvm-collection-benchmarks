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

Use the `run.sh` script at the root of the project, as it will set environment variables to ensure consistent run conditions.

```bash
# Run the whole suite of java benchmarks
./run.sh java

# Run a specific java benchmark, in this case JavaListBenchmarks.append1
./run.sh java JavaListBenchmarks.add1

# Run the whole suite of kotlin benchmarks
./run.sh kotlin

# Run a specific kotlin benchmark, in this case KotlinListBenchmarks.append1
./run.sh kotlin KotlinListBenchmarks.append1
```

## Classes Being Tested

| Collection | Language | Source   | Version | Class               |
|------------|----------|----------|---------|---------------------|
| List       | Java     | Java SDK | 19      | java.util.ArrayList |

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

| Operation              | java.util.ArrayList | io.vavr.collection.List | org.pcollections.TreePVector | kotlin.collection.List | kotlin.collection.MutableList | kotlinx.collections.immutable.PersistentList |
|------------------------|---------------------|-------------------------|------------------------------|------------------------|-------------------------------|----------------------------------------------|
| Append 1 item          | 0.162 ms/op         | 1,433.731 ms/op         |                              | 122.688 ms/op          |                               |                                              |
| Append 100 items       | 0.250 ms/op         | 1,370.798 ms/op         |                              | 128.721 ms/op          |                               |                                              |
| Prepend 1 item         | 40.690 ms/op        | 0.162 ms/op             |                              | 129.202 ms/op          |                               |                                              |
| Prepend 100 items      | 39.568 ms/op        | 0.327 ms/op             |                              | 136.102 ms/op          |                               |                                              |
| Remove 1 item at end   | 0.164 ms/op         | 0.343 ms/op             |                              |                        |                               |                                              |
| Remove 1 item at start | 43.065 ms/op        | 1,496.437 ms/op         |                              |                        |                               |                                              |

### Set Tests

| Operation     | java.util.HashSet | io.vavr.collection.HashSet | org.pcollections.HashTreePSet | kotlin.collection.Set | kotlin.collection.MutableSet | kotlinx.collections.immutable.PersistentSet |
|---------------|-------------------|----------------------------|-------------------------------|-----------------------|------------------------------|---------------------------------------------|
| Add 1 item    | 0.201 ms/op       | 1.073 ms/op                |                               |                       |                              |                                             |
| Add 100 items | 0.500 ms/op       | 4.029 ms/op                |                               |                       |                              |                                             |
| Remove 1 item | 0.289 ms/op       | 1.385 ms/op                |                               |                       |                              |                                             |

### Map Tests

| Operation     | java.util.HashMap | io.vavr.collection.HashMap | org.pcollections.HashTreePMap | kotlin.collection.Map | kotlin.collection.MutableMap | kotlinx.collections.immutable.PersistentMap |
|---------------|-------------------|----------------------------|-------------------------------|-----------------------|------------------------------|---------------------------------------------|
| Add 1 item    | 0.236 ms/op       | 0.714 ms/op                |                               |                       |                              |                                             |
| Add 100 items | 1.091 ms/op       | 5.444 ms/op                |                               |                       |                              |                                             |
| Remove 1 item | 0.360 ms/op       | 1.382 ms/op                |                               |                       |                              |                                             |

## Thoughts on the Results

### Lists

1. Prepend operations on java.util.List are extremely slow because of the need to shift the existing elements. I expected as much, but knowing that Vavr is most efficient with its prepend operations I felt it was important to include this for comparison.
2. io.vavr.collection.List requires its prepend operations to be used in order to be efficient. This can lead to counter-intuitive ordering when building a list. In addition, its remove operations appear to work best at the end of the list, not the beginning. Overall it can be an extremely efficient data structure, but it has to be used very precisely in order to achieve this.

### Sets

1. io.vavr.collection.HashSet was slower across the board than I anticipated when compared to java.util.HashSet.

### Maps

1. io.vavr.collection.HasMap was slower across the board than I anticipated when compared to java.util.HashMap.
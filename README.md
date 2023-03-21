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

| Collection | Language | Source              | Version | Class                             |
|------------|----------|---------------------|---------|-----------------------------------|
| List       | Java     | Java SDK            | 19      | java.util.ArrayList               |
| List       | Java     | Vavr                | 0.10.4  | io.vavr.collection.List           |
| List       | Java     | PCollections        | 4.0.1   | org.pcollections.TreePVector      |
| List       | Kotlin   | Kotlin Std Lib      | 1.8.10  | kotlin.collection.List            |
| List       | Kotlin   | Kotlin Std Lib      | 1.8.10  | kotlin.collection.MutableList     |
| List       | Kotlin   | Kotlinx Collections | 0.3.5   | kotlinx.collection.PersistentList |
| Set        | Java     | Java SDK            | 19      | java.util.HashSet                 |
| Set        | Java     | Vavr                | 0.10.4  | io.vavr.collection.HashSet        |
| Set        | Java     | PCollections        | 4.0.1   | org.collections.HashTreePSet      |
| Set        | Kotlin   | Kotlin Std Lib      | 1.8.10  | kotlin.collection.Set             |
| Set        | Kotlin   | Kotlin Std Lib      | 1.8.10  | kotlin.collection.MutableSet      |
| Set        | Kotlin   | Kotlinx Collections | 0.3.5   | kotlinx.collection.PersistentSet  |
| Map        | Java     | Java SDK            | 19      | java.util.HashMap                 |
| Map        | Java     | Vavr                | 0.10.4  | io.vavr.collection.HashMap        |
| Map        | Java     | PCollections        | 4.0.1   | org.pcollections.HashTreePMap     |
| Map        | Kotlin   | Kotlin Std Lib      | 1.8.10  | kotlin.collection.Map             | 
| Map        | Kotlin   | Kotlin Std Lib      | 1.8.10  | kotlin.collection.HashMap         |
| Map        | Kotlin   | Kotlinx Collections | 0.3.5   | kotlinx.collection.PersistentMap  |

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
| Append 1 item          | 0.162 ms/op         | 1,433.731 ms/op         | 0.490 ms/op                  | 122.688 ms/op          | 0.223 ms/op                   | 0.230 ms/op                                  |
| Append 100 items       | 0.250 ms/op         | 1,370.798 ms/op         | 9.214 ms/op                  | 128.721 ms/op          | 0.471 ms/op                   | 0.433 ms/op                                  |
| Prepend 1 item         | 40.690 ms/op        | 0.162 ms/op             | 1.316 ms/op                  | 129.202 ms/op          | 63.277 ms/op                  | 302.151 ms/op                                |
| Prepend 100 items      | 39.568 ms/op        | 0.327 ms/op             | 4.077 ms/op                  | 136.102 ms/op          | 64.844 ms/op                  | 189.092 ms/op                                |
| Remove 1 item at end   | 0.164 ms/op         | 0.343 ms/op             | 1.480 ms/op                  | 123.420 ms/op          | 0.171 ms/op                   | 0.225 ms/op                                  |
| Remove 1 item at start | 43.065 ms/op        | 1,496.437 ms/op         | 1.107 ms/op                  | 136.924 ms/op          | 64.074 ms/op                  | 247.828 ms/op                                |

### Set Tests

| Operation     | java.util.HashSet | io.vavr.collection.HashSet | org.pcollections.HashTreePSet | kotlin.collection.Set | kotlin.collection.MutableSet | kotlinx.collections.immutable.PersistentSet |
|---------------|-------------------|----------------------------|-------------------------------|-----------------------|------------------------------|---------------------------------------------|
| Add 1 item    | 0.201 ms/op       | 1.073 ms/op                | 1.210 ms/op                   |                       |                              |                                             |
| Add 100 items | 0.500 ms/op       | 4.029 ms/op                | 5.626 ms/op                   |                       |                              |                                             |
| Remove 1 item | 0.289 ms/op       | 1.385 ms/op                | 2.259 ms/op                   |                       |                              |                                             |

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
3. kotlinx.collection.PersistentList performs incredibly well in most areas. However it seems to have issues with random access modifications. This means changes at the end of the list (adding, removing, etc) all work great, but changes at in the middle (or worse, at the beginning) do not.
4. PTreeVector is a bit disappointing. It certainly is not a terrible performer, but it doesn't compare Vavr and kotlinx in efficiency.

### Sets

1. io.vavr.collection.HashSet was slower across the board than I anticipated when compared to java.util.HashSet.

### Maps

1. io.vavr.collection.HasMap was slower across the board than I anticipated when compared to java.util.HashMap.

## Addendum on Kotlin `MutableList`

The original round of tests on Kotlin's `MutableList` returned very different results. Here are the original numbers:

| Operation              | Original MutableList Result |
|------------------------|-----------------------------|
| Append 1 item          | 46.595 ms/op                |
| Append 100 items       | 57.418 ms/op                |
| Prepend 1 item         | 117.835 ms/op               |
| Prepend 100 items      | 121.001 ms/op               |
| Remove 1 item at end   | 0.171 ms/op                 |
| Remove 1 item at start | 64.074 ms/op                |

These numbers were very shocking because the expectation was that `MutableList` would perform on part with Java's `ArrayList` (which is the under-the-hood implementation of `MutableList`), and that was not the case. On further investigation, the root cause was determined to be capacity size.

When using the `toMutableList()` function, the resulting `MutableList` wraps an `ArrayList` which wraps an `Array` which has a max capacity equal to the exact size of the collection `toMutableList()` was invoked upon. This means that any operation that follows necessitates cloning the array into one with a higher capacity. This is why the extra cost occurred.

In the `ArrayList` tests, the list was constructed differently and it is believed that it had a higher max capacity at the conclusion of its construction. This explains the difference, and once this capacity issue was manually corrected in the setup for `MutableList` the test results became much more in line with expectations.
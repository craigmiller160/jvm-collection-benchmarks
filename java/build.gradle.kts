val projectGroup: String by project
val projectVersion: String by project

group = projectGroup
version = projectVersion

plugins {
    `java-library`
    id("me.champeau.jmh") version "0.7.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
}

jmh {
    warmupIterations.set(System.getenv("WARMUP_ITERATIONS").toInt())
    iterations.set(System.getenv("ITERATIONS").toInt())
    fork.set(System.getenv("FORK").toInt())
    batchSize.set(System.getenv("BATCH_SIZE").toInt())
    warmupBatchSize.set(System.getenv("BATCH_SIZE").toInt())
    benchmarkMode.set(listOf(System.getenv("BENCHMARK_MODE")))
    timeUnit.set(System.getenv("TIME_UNIT"))
}
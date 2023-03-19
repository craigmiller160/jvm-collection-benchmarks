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

dependencies {
    implementation("io.vavr:vavr:0.10.4")
    implementation("org.pcollections:pcollections:4.0.1")
}

jmh {
    warmupIterations.set(getenv("WARMUP_ITERATIONS", "2").toInt())
    iterations.set(getenv("ITERATIONS", "10").toInt())
    fork.set(getenv("FORK", "10").toInt())
    batchSize.set(getenv("BATCH_SIZE", "1000").toInt())
    warmupBatchSize.set(getenv("BATCH_SIZE", "1000").toInt())
    benchmarkMode.set(listOf(getenv("BENCHMARK_MODE", "AverageTime")))
    timeUnit.set(getenv("TIME_UNIT", "ns"))
    includes.set(listOf(getenv("INCLUDES", "")))
}

// Default value is necessary for IDE to run script
fun getenv(name: String, defaultValue: String): String =
    System.getenv(name) ?: defaultValue
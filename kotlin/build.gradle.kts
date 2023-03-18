import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val projectGroup: String by project
val projectVersion: String by project

group = projectGroup
version = projectVersion

plugins {
    `java-library`
    id("me.champeau.jmh") version "0.7.0"
    kotlin("jvm") version "1.8.10"
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable-jvm:0.3.5")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "19"
    }
}

jmh {
    warmupIterations.set(getenv("WARMUP_ITERATIONS", "2").toInt())
    iterations.set(getenv("ITERATIONS", "10").toInt())
    fork.set(getenv("FORK", "10").toInt())
    batchSize.set(getenv("BATCH_SIZE", "1000").toInt())
    warmupBatchSize.set(getenv("BATCH_SIZE", "1000").toInt())
    benchmarkMode.set(listOf(getenv("BENCHMARK_MODE", "AverageTime")))
    timeUnit.set(getenv("TIME_UNIT", "ns"))
}

// Default value is necessary for IDE to run script
fun getenv(name: String, defaultValue: String): String =
    System.getenv(name) ?: defaultValue
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
    val jmhVersion: String by project

    implementation("org.openjdk.jmh:jmh-core:$jmhVersion")
}

jmh {
//    warmupIterations.set(2)
//    iterations.set(2)
    fork.set(1)
    batchSize.set(1)
    warmupBatchSize.set(1)
//    includes.set(listOf("ListBenchmarks"))
//    jvmArgs.set(listOf("-Xmx4096m"))
}
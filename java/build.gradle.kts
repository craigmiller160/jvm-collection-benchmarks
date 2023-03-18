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
    implementation("org.openjdk.jmh:jmh-generator-annprocess:$jmhVersion")
}

jmh {
    warmupIterations.set(2)
    iterations.set(2)
    fork.set(2)
}
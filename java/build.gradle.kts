val projectGroup: String by project
val projectVersion: String by project

group = projectGroup
version = projectVersion

plugins {
    `java`
    id("me.champeau.gradle.jmh") version "0.5.3"
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
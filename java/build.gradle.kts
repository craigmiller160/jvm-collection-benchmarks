val projectGroup: String by project
val projectVersion: String by project

group = projectGroup
version = projectVersion

plugins {
    `java`
}

dependencies {
    val jmhVersion: String by project

        implementation("org.openjdk.jmh:jmh-core:$jmhVersion")
    implementation("org.openjdk.jmh:jmh-generator-annprocess:$jmhVersion")
}
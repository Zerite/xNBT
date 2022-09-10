plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":"))
    implementation("io.github.jglrxavpok.hephaistos:common:2.2.0")

    implementation("org.openjdk.jmh:jmh-core:1.35")
    annotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.35")
}
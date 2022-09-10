plugins {
    id("java")
}

group = "dev.zerite"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:23.0.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.getByName<JavaCompile>("compileJava") {
    options.release.set(8)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

plugins {
    kotlin("jvm") version "1.9.23"
    id("io.ktor.plugin") version "2.3.10"
}

group = "com.huanmeng-qwq.lazykook"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("com.github.SNWCreations:JKook:0.50.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")

    implementation("io.ktor:ktor-client-core-jvm")
    implementation("io.ktor:ktor-client-cio-jvm")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
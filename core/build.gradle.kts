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

application {
    mainClass = "unset"
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    api("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.+")
    api("ch.qos.logback:logback-classic:1.5.6")

    api("io.ktor:ktor-server-core-jvm")
    api("io.ktor:ktor-server-netty-jvm")

    api("io.ktor:ktor-client-core-jvm")
    api("io.ktor:ktor-client-cio-jvm")
    api("io.ktor:ktor-client-websockets")

    implementation("org.bspfsystems:yamlconfiguration:2.0.1")
    api("com.huanmeng-qwq:event-core:1.0.4"){
        exclude(group = "com.google.guava")
    }
    api("com.google.guava:guava:33.2.0-jre")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
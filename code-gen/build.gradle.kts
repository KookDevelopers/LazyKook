plugins {
    kotlin("jvm") version "2.0.20"
    id("io.github.goooler.shadow") version "8.1.8"
}

group = "com.huanmeng-qwq.lazykook"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(libs.jackson.kt)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
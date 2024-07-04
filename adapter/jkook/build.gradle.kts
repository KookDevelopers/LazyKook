plugins {
    kotlin("jvm") version "2.0.0"
    id("io.ktor.plugin") version "2.3.12"
}

group = "com.huanmeng-qwq.lazykook"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(libs.jkook)
    implementation(project(":lazykook"))
}

tasks.shadowJar {
    enabled = false
}

application {
    mainClass = "unset"
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
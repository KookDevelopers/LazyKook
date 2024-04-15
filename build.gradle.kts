plugins {
    kotlin("jvm") version "1.9.22"
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
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
plugins {
    `java-library`
}

tasks.create("target") {
    dependsOn(tasks.build)
}

tasks.test {
    onlyIf { !gradle.startParameter.taskNames.contains("target") }
}
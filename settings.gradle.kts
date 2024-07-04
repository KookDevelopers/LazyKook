plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "LazyKook-parent"
include("code-gen")

addProject("core", "lazykook")
addProject(":adapter:jkook", "lazykook-adapter-jkook")

fun addProject(projectPath: String, name: String) {
    include(projectPath)
    findProject(projectPath.let {
        if(it.startsWith(":")) it else ":$it"
    })!!.name = name
}
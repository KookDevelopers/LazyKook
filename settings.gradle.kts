plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "LazyKook"
include("adapter:jkook")
include("core")
findProject(":adapter:jkook")?.name = "jkook"
include("code-gen")

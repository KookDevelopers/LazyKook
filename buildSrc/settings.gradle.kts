dependencyResolutionManagement {
    versionCatalogs {
        register("libs") {
            from(files("../gradle/libs.versions.toml")) // include from parent project
        }
    }
}
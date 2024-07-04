allprojects {
    version = "0.1.0"
}

subprojects {
    if (name != "code-gen") {
        apply(plugin = "me.huanmeng.lazykook.publish-conventions")
    }
}
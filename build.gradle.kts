allprojects {
    version = "0.1.0"
}

subprojects {
    if (name != "code-gen" && name != "adapter") {
        apply(plugin = "me.huanmeng.lazykook.publish-conventions")
        apply(plugin = "me.huanmeng.lazykook.build-conventions")
    }
}
allprojects {
    version = "0.2.0"
}

subprojects {
    if (name != "code-gen" && name != "adapter") {
        apply(plugin = "me.huanmeng.lazykook.publish-conventions")
        apply(plugin = "me.huanmeng.lazykook.build-conventions")
    }
}
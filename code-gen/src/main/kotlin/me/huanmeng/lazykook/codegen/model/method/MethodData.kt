package me.huanmeng.lazykook.codegen.model.method

/**
 * 2024/6/29<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class MethodData(
    val name: String,
    val body: String
) {
    fun write(sb: StringBuilder) {
        sb.appendLine("\tfun $name {")
        sb.appendLine("\t\t$body")
        sb.appendLine("\t}")
    }
}

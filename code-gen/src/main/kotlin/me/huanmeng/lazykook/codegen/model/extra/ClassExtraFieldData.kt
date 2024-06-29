package me.huanmeng.lazykook.codegen.model.extra

/**
 * 2024/6/29<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class ClassExtraFieldData(
    val modifier: String,
    val type: String,
    val default: String? = null,
    val get: String? = null,
) {
    fun write(sb: StringBuilder, name: String) {
        sb.append("\t").append(modifier).append(' ').append(name).append(": ").append(type)
        if (get != null) {
            sb.appendLine()
            sb.appendLine("\t\tget() = $get")
        } else {
            sb.appendLine(" = $default")
        }
    }
}
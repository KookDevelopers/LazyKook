package me.huanmeng.lazykook.codegen

/**
 * 2024/6/15<br></br>
 * LazyKook<br></br>
 *
 * @author huanmeng_qwq
 */
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val dest = if (args.isNotEmpty()) args[0] else ""
        if (dest.isBlank()) {
            println("<dest:Path>")
            return
        }

    }
}

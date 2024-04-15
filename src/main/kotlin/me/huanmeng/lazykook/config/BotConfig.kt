package me.huanmeng.lazykook.config

/**
 * 2024/4/15<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class BotConfig(
    var token: String? = null,
    var mode: SocketMode = SocketMode.WEBSOCKET,
    var webhook: WebHookConfig? = null,
)

enum class SocketMode(vararg alias: String) {
    WEBSOCKET("ws", "websocket"),
    WEBHOOK("wh", "webhook"),
    ;

    val alias: List<String> = alias.toList()

    companion object {
        fun getByAlias(alias: String): SocketMode? {
            return entries.firstOrNull { it.name.lowercase() == alias.lowercase() || it.alias.contains(alias.lowercase()) }
        }
    }
}

data class WebHookConfig(
    var url: String? = null,
    var port: Int = 8080,
    var path: String = "/kook"
)
package me.huanmeng.lazykook.config

/**
 * 2024/4/15<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class BotConfig(
    val token: String,
    val httpConfig: HttpConfig = HttpConfig(),
    val mode: SocketMode = SocketMode.WEBSOCKET,
    val webhook: WebHookConfig? = null,
)

data class HttpConfig(
    val url: String = "https://www.kookapp.cn/api",
    val timeout: Long = 3000,
)

enum class SocketMode(vararg alias: String) {
    WEBSOCKET("ws", "websocket"),
    WEBHOOK("wh", "webhook"),
    ;

    val alias: List<String> = alias.toList()

    companion object {
        fun find(alias: String): SocketMode? {
            return entries.firstOrNull { it.name.lowercase() == alias.lowercase() || it.alias.contains(alias.lowercase()) }
        }
    }
}

data class WebHookConfig(
    var url: String? = null,
    var port: Int = 8080,
    var path: String = "/kook"
)
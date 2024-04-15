package me.huanmeng.lazykook

import me.huanmeng.lazykook.config.BotConfig
import me.huanmeng.lazykook.http.KHttp

/**
 * 2024/4/15<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class LazyKook(val config: BotConfig) {
    val http: KHttp = KHttp(this)
    val isOnline: Boolean
        get() = TODO()

}
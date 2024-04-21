package me.huanmeng.lazykook

import me.huanmeng.lazykook.config.BotConfig
import me.huanmeng.lazykook.event.EventManager
import me.huanmeng.lazykook.http.KHttp
import me.huanmeng.lazykook.http.Requests
import me.huanmeng.lazykook.http.request.GatewayRequest
import me.huanmeng.lazykook.ws.WebSocketClient

/**
 * 2024/4/15<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
open class LazyKook(val config: BotConfig) {
    val http: KHttp = KHttp(this)
    val eventManager = EventManager()

    @Suppress("MemberVisibilityCanBePrivate")
    protected var _isRunning: Boolean = false
    val isRunning: Boolean
        get() = _isRunning


    suspend fun start() {
        if (isRunning) {
            return
        }
        _isRunning = true

        val resp = http.http(Requests.GATEWAY, GatewayRequest())
        WebSocketClient(resp.url, this).start()
    }

    fun stop() {
        if (!isRunning) {
            return
        }
        _isRunning = false
    }
}
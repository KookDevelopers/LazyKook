package me.huanmeng.lazykook

import me.huanmeng.lazykook.config.BotConfig
import me.huanmeng.lazykook.event.EventManager
import me.huanmeng.lazykook.http.KHttp
import me.huanmeng.lazykook.http.Requests
import me.huanmeng.lazykook.http.request.GatewayRequest
import me.huanmeng.lazykook.service.StorageService
import me.huanmeng.lazykook.webhook.WebHookServer
import me.huanmeng.lazykook.ws.WebSocketClient

/**
 * 2024/4/15<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
open class LazyKook(val config: BotConfig) {
    val http = KHttp(this)
    val eventManager = EventManager()
    val storageService = StorageService(this)

    var isRunning: Boolean = false
        private set

    suspend fun start() {
        if (isRunning) {
            return
        }
        isRunning = true
        when (config.mode) {
            me.huanmeng.lazykook.config.SocketMode.WEBSOCKET -> {
                val resp = http.http(Requests.GATEWAY, GatewayRequest())
                WebSocketClient(resp.url, this).start()
            }

            me.huanmeng.lazykook.config.SocketMode.WEBHOOK -> {
                WebHookServer(this).start()
            }
        }
    }

    fun stop() {
        if (!isRunning) {
            return
        }
        isRunning = false
    }
}
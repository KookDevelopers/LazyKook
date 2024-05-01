package me.huanmeng.lazykook.webhook

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async
import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.event.type.ReceiverSignalEvent
import me.huanmeng.lazykook.event.type.SignalExtraDataEvent
import me.huanmeng.lazykook.event.type.SignalExtraEvent
import me.huanmeng.lazykook.mapper
import me.huanmeng.lazykook.signal.Signal
import me.huanmeng.lazykook.signal.event.SignalData

/**
 * 2024/5/2<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class WebHookServer(private val root: LazyKook) {
    private val webhook = root.config.webhook ?: throw IllegalArgumentException("webhook config is null")
    fun start() {
        embeddedServer(Netty, port = webhook.port, host = webhook.host) {
            routing {
                post(webhook.path) {
                    val json = call.receiveText()
                    val signal = mapper.readValue(json, Signal::class.java)
                    if (signal.data is WebhookSignalData) {
                        val challenge = signal.data.challenge
                        call.respondText("{\"challenge\": \"$challenge\"}", ContentType.Application.Json)
                        return@post
                    } else if (signal.data is SignalData) {
                        // 立即返回信息 异步处理逻辑
                        async {
                            root.eventManager.postEvent(ReceiverSignalEvent(signal))
                            root.eventManager.postEvent(SignalExtraEvent(signal, signal.data))
                            root.eventManager.postEvent(SignalExtraDataEvent(signal, signal.data, signal.data.extra))
                        }
                        call.respondText("LazyKook")
                        return@post
                    }
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }
        }.start()
    }
}
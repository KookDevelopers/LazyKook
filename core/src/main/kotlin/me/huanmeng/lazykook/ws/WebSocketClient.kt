package me.huanmeng.lazykook.ws

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.event.type.*
import me.huanmeng.lazykook.mapper
import me.huanmeng.lazykook.signal.Signal
import me.huanmeng.lazykook.signal.event.SignalData
import me.huanmeng.lazykook.uncompress
import kotlin.math.min

/**
 * 2024/4/17<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class WebSocketClient(private val url: String, val root: LazyKook) {
    private var nextPingDelay = 1000 * 26
    private var lastPingTime = System.currentTimeMillis()
    private var lastPongTime = System.currentTimeMillis()
    private val client = HttpClient(CIO) {
        install(WebSockets)
    }

    var sn: Int = 0
        private set

    suspend fun start() {
        client.webSocket(url) {
            launch {
                while (root.isRunning) {
                    if (System.currentTimeMillis() > lastPingTime + min(nextPingDelay, 1000 * 33)) {
                        root.eventManager.postEvent(SignalPingEvent)
                        send("{\"s\":2,\"sn\":$sn}")
                        nextPingDelay = 1000 * 26
                        lastPingTime = System.currentTimeMillis()
                    }
                }
                this@launch.cancel()
                this@webSocket.cancel()
            }
            while (root.isRunning) {
                val frame = incoming.receive()
                when (frame.frameType) {
                    io.ktor.websocket.FrameType.TEXT -> {
                        frame as Frame.Text
                        val message = frame.readText()
                        process(message)
                    }

                    io.ktor.websocket.FrameType.BINARY -> {
                        frame as Frame.Binary
                        val bytes = frame.readBytes().uncompress()
                        val message = String(bytes)
                        process(message)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun process(message: String) {
        var snChanged = false
        try {
            val signal = mapper.readValue(message, Signal::class.java)
            if (signal.signal != -99) {
                snChanged = true
                sn = signal.signal
                nextPingDelay += 500
            }
            root.eventManager.postEvent(ReceiverSignalEvent(signal))
            when (signal.type) {
                1 -> {
                    root.eventManager.postEvent(SignalHelloEvent(signal))
                }

                0 -> {
                    if (signal.data is SignalData) {
                        root.eventManager.postEvent(SignalExtraEvent(signal, signal.data))
                        root.eventManager.postEvent(SignalExtraDataEvent(signal, signal.data, signal.data.extra))
                    }
                }

                3 -> {
                    lastPongTime = System.currentTimeMillis()
                    root.eventManager.postEvent(SignalPongEvent(signal))
                }
            }
        } catch (e: Exception) {
            if (!snChanged) {
                val node = mapper.readTree(message)
                if (node.has("sn")) {
                    sn = node.get("sn").asInt()
                    nextPingDelay += 10
                }
            }
            RuntimeException(message, e).printStackTrace()
        }
    }
}
package me.huanmeng.lazykook.ws

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.launch
import me.huanmeng.lazykook.mapper
import me.huanmeng.lazykook.signal.Signal
import me.huanmeng.lazykook.uncompress
import kotlin.math.min

/**
 * 2024/4/17<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class WebSocketClient(private val url: String) {
    var nextPingDelay = 1000 * 26
    var lastPingTime = System.currentTimeMillis()
    var sn: Int = 0
    val client = HttpClient(CIO) {
        install(WebSockets)
    }

    suspend fun start() {
        client.webSocket(url) {
            launch {
                while (true) {
                    if (System.currentTimeMillis() > lastPingTime + min(nextPingDelay, 1000 * 35)) {
                        send("{\"s\":2,\"sn\":$sn}")
                        nextPingDelay = 1000 * 26
                    }
                }
            }
            while (true) {
                val frame = incoming.receive()
                when (frame.frameType) {
                    io.ktor.websocket.FrameType.TEXT -> {
                        frame as Frame.Text
                        val message = frame.readText()
                        val signal = mapper.readValue(message, Signal::class.java)
                        if (signal.signal != -99) {
                            sn = signal.signal
                            nextPingDelay += 500
                        }
                        processSignal(message, signal)
                    }

                    io.ktor.websocket.FrameType.BINARY -> {
                        frame as Frame.Binary
                        val bytes = frame.readBytes().uncompress()
                        val message = String(bytes)
                        val signal = mapper.readValue(message, Signal::class.java)
                        if (signal.signal != -99) {
                            sn = signal.signal
                            nextPingDelay += 500
                        }
                        processSignal(message, signal)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun processSignal(json: String, signal: Signal) {
    }
}
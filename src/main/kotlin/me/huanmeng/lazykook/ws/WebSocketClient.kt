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
    private var nextPingDelay = 1000 * 26
    private var lastPingTime = System.currentTimeMillis()
    private var lastPongTime = System.currentTimeMillis()
    private var _sn: Int = 0
    private val client = HttpClient(CIO) {
        install(WebSockets)
    }

    val sn: Int
        get() = _sn

    suspend fun start() {
        client.webSocket(url) {
            launch {
                while (true) {
                    if (System.currentTimeMillis() > lastPingTime + min(nextPingDelay, 1000 * 35)) {
                        send("{\"s\":2,\"sn\":$_sn}")
                        nextPingDelay = 1000 * 26
                        lastPingTime = System.currentTimeMillis()
                    }
                }
            }
            while (true) {
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
        val signal = mapper.readValue(message, Signal::class.java)
        if (signal.signal != -99) {
            _sn = signal.signal
            nextPingDelay += 500
        }
        when (signal.type) {
            1 -> {
            }

            0 -> {
            }

            3 -> {
                lastPongTime = System.currentTimeMillis()
            }
        }
    }
}
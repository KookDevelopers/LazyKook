package me.huanmeng.lazykook.feat

import kotlinx.coroutines.runBlocking
import me.huanmeng.lazykook.BotTest
import me.huanmeng.lazykook.http.Requests
import me.huanmeng.lazykook.http.request.VoiceJoinRequest
import me.huanmeng.lazykook.http.request.VoiceLeaveRequest
import org.junit.jupiter.api.Test

/**
 * 2024/7/4<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class VoiceTest : BotTest() {

    @Test
    fun join(): Unit = runBlocking {
        val res = bot.http.http(Requests.Voice.JOIN, VoiceJoinRequest("2747255778912727"))
        println("-map 0:a:0 -acodec libopus -ab 48k -ac 2 -ar 48000 -filter:a volume=0.8 -f tee '[select=a:f=rtp:ssrc=48:payload_type=${res.audio_pt}]rtp://${res.ip}:${res.port}?rtcpport=${res.rtcp_port}'")
    }

    @Test
    fun leave(): Unit = runBlocking {
        val res = bot.http.http(Requests.Voice.LEAVE, VoiceLeaveRequest("2747255778912727"))
        println(res)
    }

    @Test
    fun list(): Unit = runBlocking {
        val res = bot.http.http(Requests.Voice.LIST, null)
        println(res.items)
    }
}
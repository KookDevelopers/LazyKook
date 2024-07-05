package me.huanmeng.lazykook.feat

import kotlinx.coroutines.runBlocking
import me.huanmeng.lazykook.BotTest
import me.huanmeng.lazykook.http.Requests
import me.huanmeng.lazykook.http.request.VoiceLeaveRequest
import me.huanmeng.lazykook.voice.VoiceStream
import java.io.File
import kotlin.test.Test

/**
 * 2024/7/4<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class VoiceTest : BotTest() {

    @Test
    fun join(): Unit = runBlocking {
        val file = File("C:\\Users\\huanmeng\\Downloads\\Music\\千本樱.mp3")
        val stream = VoiceStream("ffmpeg", bot, "3085835434214897")
        stream.addAudio(file)
        stream.addAudio(file)
        stream.addAudio(file)
    }

    @Test
    fun leave(): Unit = runBlocking {
        val res = bot.http.http(Requests.Voice.LEAVE, VoiceLeaveRequest("39698118117509700"))
        println(res)
    }

    @Test
    fun list(): Unit = runBlocking {
        val res = bot.http.http(Requests.Voice.LIST, null)
        println(res.items)
    }
}
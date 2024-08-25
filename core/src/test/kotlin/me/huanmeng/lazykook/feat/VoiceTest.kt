package me.huanmeng.lazykook.feat

import kotlinx.coroutines.runBlocking
import me.huanmeng.lazykook.BotTest
import me.huanmeng.lazykook.http.Requests
import me.huanmeng.lazykook.http.request.VoiceLeaveRequest
import me.huanmeng.lazykook.voice.PlaylistStream
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
        val stream = PlaylistStream("ffmpeg", bot, "7507759824750561")
        var index = 0
        while (true) {
            val dir = File("C:\\Users\\huanmeng\\Downloads\\Music")
            val def = arrayOf(File("C:\\Users\\huanmeng\\Downloads\\Music\\千本樱.mp3"))
            val listFiles = dir.listFiles()?.let {
                if (it.isEmpty()) def else it
            } ?: def
            val file = listFiles[index % listFiles.size]
            ++index
            println(file.absolutePath)
            stream.addAudio(file)
        }
        stream.close()
    }

    @Test
    fun leave(): Unit = runBlocking {
        val res = bot.http.http(Requests.Voice.LEAVE, VoiceLeaveRequest("3969811811750970"))
        println(res)
    }

    @Test
    fun list(): Unit = runBlocking {
        val res = bot.http.http(Requests.Voice.LIST, null)
        println(res.items)
    }
}
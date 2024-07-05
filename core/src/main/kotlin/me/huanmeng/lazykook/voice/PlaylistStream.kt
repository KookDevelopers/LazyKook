package me.huanmeng.lazykook.voice

import cn.hutool.core.net.NetUtil
import kotlinx.coroutines.runBlocking
import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.http.Requests
import me.huanmeng.lazykook.http.request.VoiceJoinRequest
import me.huanmeng.lazykook.http.response.VoiceJoinResponse

/**
 * 2024/7/5<br>
 * LazyKook-parent<br>
 * @author huanmeng_qwq
 */
class PlaylistStream(ffmpegExecutable: String, bot: LazyKook, channelId: String) :
    VoiceStream(ffmpegExecutable, bot, channelId) {
    protected var requestSkip: Boolean = false
    val playlistPort = NetUtil.getUsableLocalPort()
    var playlist: Process? = null


    override fun buildArgument(res: VoiceJoinResponse): String {
        return super.buildArgument(res).replace("-i - ", "-i zmq:tcp://127.0.0.1:${playlistPort} ")
    }

    override suspend fun addAudio(bytes: ByteArray) {
        checkInit {
            for (byte in bytes) {
                if (requestSkip) {
                    requestSkip = false
                    return@checkInit
                }
                outputStream.write(byte.toInt())
            }
        }
    }

    override suspend fun checkInit(then: (Process.() -> Unit)) {
        if (playlist != null && executor != null) {
            playlist!!.then()
            return
        }
        runBlocking {
            val res = bot.http.http(Requests.Voice.JOIN, VoiceJoinRequest(channelId))
            val executorCmd = ("\"${ffmpegExecutable}\" " + buildArgument(res)).split(" ").toTypedArray()
            val cmd = "ffmpeg -re -nostats -i - -acodec libopus -ab 128k -f mpegts zmq:tcp://127.0.0.1:${playlistPort}"
            executor = Runtime.getRuntime().exec(executorCmd)
            playlist = Runtime.getRuntime().exec(cmd).also(then)
        }
    }

    override suspend fun leaveChannel() {
        if (playlist != null) {
            playlist?.destroy()
            playlist = null
        }
        super.leaveChannel()
    }

    fun skip() {
        requestSkip = true
    }
}
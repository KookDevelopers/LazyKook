package me.huanmeng.lazykook.voice

import kotlinx.coroutines.runBlocking
import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.http.Requests
import me.huanmeng.lazykook.http.request.VoiceJoinRequest
import me.huanmeng.lazykook.http.request.VoiceLeaveRequest
import me.huanmeng.lazykook.http.response.VoiceJoinResponse
import java.io.File
import java.io.InputStream

/**
 * 2024/7/5<br>
 * LazyKook-parent<br>
 * @author huanmeng_qwq
 */
@Suppress("MemberVisibilityCanBePrivate")
open class VoiceStream(
    protected val ffmpegExecutable: String,
    protected val bot: LazyKook,
    protected val channelId: String
) :
    AutoCloseable {
    var executor: Process? = null

    protected open fun buildArgument(res: VoiceJoinResponse): String {
        val rtpUri = "rtp://${res.ip}:${res.port}".let {
            if (res.port.toInt() == res.rtcp_port) it else it + "?rtcpport=${res.rtcp_port}"
        }
        val b = res.bitrate / 1000
        val bitrate = if (b > 48) 48 else b - 20
        val args =
            "-re -stream_loop -1 -i - -map 0:a:0 -acodec libopus -ab ${bitrate}k -ac 2 -ar ${bitrate * 1000} -filter:a volume=0.8 -f tee '[select=a:f=rtp:ssrc=${res.audio_ssrc}:payload_type=${res.audio_pt}]${rtpUri}'"
        return args
    }

    open suspend fun addAudio(bytes: ByteArray) {
        checkInit {
            for (byte in bytes) {
                outputStream.write(byte.toInt())
            }
        }
    }

    suspend fun addAudio(inputStream: InputStream) {
        addAudio(inputStream.readBytes())
    }


    suspend fun addAudio(file: File) {
        addAudio(file.readBytes())
    }

    protected open suspend fun checkInit(then: (Process.() -> Unit)) {
        if (executor != null) {
            executor!!.then()
            return
        }
        runBlocking {
            val res = bot.http.http(Requests.Voice.JOIN, VoiceJoinRequest(channelId))
            val executorCmd = ("\"${ffmpegExecutable}\" " + buildArgument(res)).split(" ").toTypedArray()
            executor = Runtime.getRuntime().exec(executorCmd).also(then)
        }
    }

    open suspend fun leaveChannel() {
        if (executor == null) {
            return
        }
        executor?.destroy()
        executor = null
        bot.http.http(Requests.Voice.LEAVE, VoiceLeaveRequest(channelId))
    }

    override fun close() {
        runBlocking {
            leaveChannel()
        }
    }
}
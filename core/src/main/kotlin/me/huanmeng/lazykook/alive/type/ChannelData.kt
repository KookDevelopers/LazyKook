package me.huanmeng.lazykook.alive.type

import me.huanmeng.lazykook.alive.AliveData
import me.huanmeng.lazykook.annotation.ByName
import me.huanmeng.lazykook.entity.Channel

/**
 * 2024/4/30<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class ChannelData(id: String) : AliveData<ChannelData>("id", id) {
    @ByName("id")
    val channelId: String by this
    val guildId: String by this

    override fun update(data: ChannelData) {
        // unsupported
    }

    fun update(channel: Channel) {
        copyFrom(channel)
    }
}
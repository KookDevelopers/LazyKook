/**
 * This file is generated automatically, please do not modify it
 */
package me.huanmeng.lazykook.alive.type

import me.huanmeng.lazykook.alive.AliveData

class ChannelData(id: String) : AliveData<ChannelData>("id", id) {
    val guildId: String by this

    @me.huanmeng.lazykook.annotation.ByName("id")
    val channelId: String by this

    fun update(t: me.huanmeng.lazykook.entity.Channel) {
        copyFrom(t)
    }


    override fun update(data: ChannelData) {
    }
}

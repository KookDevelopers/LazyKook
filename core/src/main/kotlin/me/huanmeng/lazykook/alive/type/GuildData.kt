package me.huanmeng.lazykook.alive.type

import me.huanmeng.lazykook.alive.AliveData
import me.huanmeng.lazykook.entity.Guild
import me.huanmeng.lazykook.entity.GuildInfo
import me.huanmeng.lazykook.http.response.GuildViewResponse

/**
 * 2024/4/20<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class GuildData(id: String) : AliveData<GuildData>("guildId", id) {
    val guildId: String by this
    val name: String by this
    override fun update(data: GuildData) {
        // unsupported
    }

    fun update(guild: Guild) {
        copyFrom(guild)
    }

    fun update(guild: GuildInfo) {
        copyFrom(guild)
    }

    fun update(response: GuildViewResponse) {
        copyFrom(response)
    }
}
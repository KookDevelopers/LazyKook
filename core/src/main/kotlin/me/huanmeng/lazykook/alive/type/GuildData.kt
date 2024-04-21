package me.huanmeng.lazykook.alive.type

import me.huanmeng.lazykook.alive.AliveData

/**
 * 2024/4/20<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class GuildData : AliveData<GuildData>() {
    val guildId: String by this
    override fun update(data: GuildData) {
        // unsupported
    }

    override fun id(): String {
        return guildId
    }
}
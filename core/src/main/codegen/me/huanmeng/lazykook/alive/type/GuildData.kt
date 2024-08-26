/**
 * This file is generated automatically, please do not modify it
 */
package me.huanmeng.lazykook.alive.type

import me.huanmeng.lazykook.alive.AliveData

class GuildData(id: String) : AliveData<GuildData>("guildId", id) {
    val name: String by this

    fun update(t: me.huanmeng.lazykook.entity.Guild) {
        copyFrom(t)
    }

    fun update(t: me.huanmeng.lazykook.entity.GuildInfo) {
        copyFrom(t)
    }

    fun update(t: me.huanmeng.lazykook.http.response.GuildViewResponse) {
        copyFrom(t)
    }

    override fun update(data: GuildData) {
    }
}

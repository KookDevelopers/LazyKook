package me.huanmeng.lazykook.service

import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.alive.GuildManager
import me.huanmeng.lazykook.alive.type.GuildData
import me.huanmeng.lazykook.entity.Guild
import me.huanmeng.lazykook.entity.GuildInfo
import me.huanmeng.lazykook.http.Requests
import me.huanmeng.lazykook.http.request.GuildViewRequest
import me.huanmeng.lazykook.http.response.GuildViewResponse

/**
 * 2024/4/22<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class StorageService(val lazyKook: LazyKook) {
    private val guildManager = GuildManager()

    fun updateGuild(guildId: String, data: Any): GuildData {
        val guildData = guildManager.getOrAlive(guildId) {
            GuildData(guildId)
        }

        if (data is Guild) {
            guildData.update(data)
        } else if (data is GuildInfo) {
            guildData.update(data)
        } else if (data is GuildViewResponse) {
            guildData.update(data)
        }
        return guildData
    }

    suspend fun queryGuild(guildId: String): GuildData? {
        return guildManager[guildId] ?: run {
            try {
                val response = lazyKook.http.http(Requests.Guild.VIEW, GuildViewRequest(guildId))
                guildManager.alive(GuildData(guildId).also { it.update(response) })
            } catch (_: Exception) {
                null
            }
        }
    }
}
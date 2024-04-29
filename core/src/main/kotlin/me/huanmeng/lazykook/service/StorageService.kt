package me.huanmeng.lazykook.service

import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.alive.GuildManager
import me.huanmeng.lazykook.alive.RoleManager
import me.huanmeng.lazykook.alive.UserManager
import me.huanmeng.lazykook.alive.type.GuildData
import me.huanmeng.lazykook.alive.type.UserData
import me.huanmeng.lazykook.entity.Guild
import me.huanmeng.lazykook.entity.GuildInfo
import me.huanmeng.lazykook.http.Requests
import me.huanmeng.lazykook.http.request.GuildViewRequest
import me.huanmeng.lazykook.http.request.UserViewRequest
import me.huanmeng.lazykook.http.response.GuildViewResponse

/**
 * 2024/4/22<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class StorageService(val lazyKook: LazyKook) {
    private val guildManager = GuildManager()
    private val userManager = UserManager()
    private val roleManagers: MutableMap<String, RoleManager> = mutableMapOf()

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

    suspend fun findGuild(guildId: String): GuildData? {
        return guildManager[guildId] ?: run {
            try {
                val response = lazyKook.http.http(Requests.Guild.VIEW, GuildViewRequest(guildId))
                guildManager.alive(GuildData(response.id).also { it.update(response) })
            } catch (_: Exception) {
                null
            }
        }
    }

    suspend fun findUser(userId: String, guildId: String? = null): UserData? {
        return userManager[userId] ?: run {
            try {
                val response = lazyKook.http.http(Requests.User.VIEW, UserViewRequest(userId, guildId))
                userManager.alive(UserData(response.id).also {
                    it.update(response)
                    if (guildId != null) {
                        it.updateNickname(guildId, response.nickname)
                    }
                })
            } catch (_: Exception) {
                null
            }
        }
    }
}
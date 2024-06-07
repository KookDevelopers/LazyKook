package me.huanmeng.lazykook.service

import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.alive.ChannelManager
import me.huanmeng.lazykook.alive.GuildManager
import me.huanmeng.lazykook.alive.RoleManager
import me.huanmeng.lazykook.alive.UserManager
import me.huanmeng.lazykook.alive.type.ChannelData
import me.huanmeng.lazykook.alive.type.GuildData
import me.huanmeng.lazykook.alive.type.RoleData
import me.huanmeng.lazykook.alive.type.UserData
import me.huanmeng.lazykook.entity.Guild
import me.huanmeng.lazykook.entity.GuildInfo
import me.huanmeng.lazykook.entity.Role
import me.huanmeng.lazykook.http.Requests
import me.huanmeng.lazykook.http.request.ChannelViewRequest
import me.huanmeng.lazykook.http.request.GuildViewRequest
import me.huanmeng.lazykook.http.request.RoleListRequest
import me.huanmeng.lazykook.http.request.UserViewRequest
import me.huanmeng.lazykook.http.response.GuildViewResponse

/**
 * 2024/4/22<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class StorageService(val lazyKook: LazyKook) {
    val guildManager = GuildManager()
    val userManager = UserManager()
    val channelManager = ChannelManager()
    val roleManagers: MutableMap<String, RoleManager> = mutableMapOf()

    lateinit var botUser: UserData
        private set

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

    suspend fun findGuild(guildId: String): GuildData {
        return guildManager[guildId] ?: run {
            try {
                val response = lazyKook.http.http(Requests.Guild.VIEW, GuildViewRequest(guildId))
                guildManager.alive(GuildData(response.id).also { it.update(response) })
            } catch (e: Exception) {
                throw e
            }
        }
    }

    suspend fun findUser(userId: String, guildId: String? = null): UserData {
        return userManager[userId] ?: run {
            try {
                val response = lazyKook.http.http(Requests.User.VIEW, UserViewRequest(userId, guildId))
                userManager.alive(UserData(response.id).also {
                    it.update(response)
                    if (guildId != null) {
                        it.updateNickname(guildId, response.nickname)
                    }
                })
            } catch (e: Exception) {
                throw e
            }
        }
    }

    suspend fun findMe(): UserData {
        return if (::botUser.isInitialized) botUser else run {
            try {
                val response = lazyKook.http.http(Requests.User.ME, null)
                userManager.alive(UserData(response.id).also {
                    it.update(response)
                    botUser = it
                })
            } catch (e: Exception) {
                throw e
            }
        }
    }

    suspend fun findRole(guildId: String, roleId: String): RoleData {
        if (roleManagers[guildId] == null) {
            roleManagers[guildId] = RoleManager()
        }
        val roleManager = roleManagers[guildId]!!
        return roleManager[roleId] ?: run {
            try {
                val response = lazyKook.http.http(Requests.Role.LIST, RoleListRequest(guildId))
                val list: MutableList<Role> = mutableListOf()
                list.addAll(response.items)
                if (response.meta.page < response.meta.pageTotal) {
                    for (i in 2..response.meta.pageTotal) {
                        val pageResponse = lazyKook.http.http(Requests.Role.LIST, RoleListRequest(guildId, i))
                        list.addAll(pageResponse.items)
                    }
                }
                for (role in list) {
                    roleManager.getOrAlive(role.roleId.toString()) {
                        RoleData(role.roleId.toString()).also {
                            it.update(role)
                        }
                    }
                }
                roleManager[roleId] ?: throw NullPointerException()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    suspend fun findChannel(channelId: String): ChannelData {
        return channelManager[channelId] ?: run {
            try {
                val response = lazyKook.http.http(Requests.Channel.VIEW, ChannelViewRequest(channelId, true))
                channelManager.alive(ChannelData(response.id).also {
                    it.update(response)
                })
            } catch (e: Exception) {
                throw e
            }
        }
    }
}
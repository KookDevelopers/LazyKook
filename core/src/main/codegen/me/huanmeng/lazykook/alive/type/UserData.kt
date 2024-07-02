/**
 * This file is generated automatically, please do not modify it
 */
package me.huanmeng.lazykook.alive.type

import me.huanmeng.lazykook.alive.AliveData

class UserData(id: String) : AliveData<UserData>("id", id) {
    init {
        aliasKey += "userId"
    }

    val bot: Boolean by this
    val roles: Array<Int> by this
    val mobileVerified: Boolean by this
    val online: Boolean by this
    val identifyNum: String
        get() {
            return visit<Any, Any>("identify_num").toString()
        }
    val avatar: String by this

    @me.huanmeng.lazykook.annotation.ByName("id")
    val userId: String by this
    val vipAvatar: String by this
    val status: Int by this
    val username: String by this
    val nickname: String?
        get() = _nickname.firstNotNullOfOrNull { it.value }
    private val _nickname: MutableMap<String, String> = hashMapOf()

    fun updateNickname(guildId: String, nickname: String) {
        this._nickname[guildId] = nickname
    }

    fun getNickname(guildId: String): String? {
        return _nickname[guildId]
    }

    fun getNicknames(): Map<String, String> {
        return _nickname.toMap()
    }

    fun update(t: me.huanmeng.lazykook.entity.User) {
        copyFrom(t)
    }

    fun update(t: me.huanmeng.lazykook.entity.UserInfo) {
        copyFrom(t)
    }


    override fun update(data: UserData) {
    }
}

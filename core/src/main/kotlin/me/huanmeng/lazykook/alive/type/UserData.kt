package me.huanmeng.lazykook.alive.type

import me.huanmeng.lazykook.alive.AliveData
import me.huanmeng.lazykook.entity.User
import me.huanmeng.lazykook.entity.UserInfo

/**
 * 2024/4/29<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class UserData : AliveData<UserData>("id", "") {
    init {
        aliasKey += "userId"
    }

    val username: String by this
    val nickname: String?
        get() = _nickname.firstNotNullOfOrNull { it.value }

    private val _nickname: MutableMap<String, String> = hashMapOf()

    override fun update(data: UserData) {
        // unsupported
    }

    fun update(user: User) {
        copyFrom(user)
    }

    fun update(userInfo: UserInfo) {
        copyFrom(userInfo)
    }

    fun updateNickname(guildId: String, nickname: String) {
        this._nickname[guildId] = nickname
    }

    fun getNickname(guildId: String): String? {
        return _nickname[guildId]
    }

    fun getNicknames(): Map<String, String> {
        return _nickname.toMap()
    }
}
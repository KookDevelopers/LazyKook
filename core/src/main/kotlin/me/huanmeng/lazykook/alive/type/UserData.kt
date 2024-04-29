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

    override fun update(data: UserData) {
        // unsupported
    }

    fun update(user: User) {
        copyFrom(user)
    }

    fun update(userInfo: UserInfo) {
        copyFrom(userInfo)
    }
}
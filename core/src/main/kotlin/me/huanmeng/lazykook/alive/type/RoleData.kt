package me.huanmeng.lazykook.alive.type

import me.huanmeng.lazykook.alive.AliveData
import me.huanmeng.lazykook.entity.Role

/**
 * 2024/4/29<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class RoleData(id: String) : AliveData<RoleData>("roleId", id) {
    val roleId: String by this
    override fun update(data: RoleData) {
        // unsupported
    }

    fun update(role: Role) {
        copyFrom(role)
    }
}
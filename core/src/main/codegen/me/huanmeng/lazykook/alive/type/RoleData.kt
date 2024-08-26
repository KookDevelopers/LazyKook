/**
 * This file is generated automatically, please do not modify it
 */
package me.huanmeng.lazykook.alive.type

import me.huanmeng.lazykook.alive.AliveData

class RoleData(id: String) : AliveData<RoleData>("id", id) {
    val color: Int by this
    val permissions: Int by this
    val roleId: Int
        get() {
            return visit<Any, Any>("roleId").toString().toInt()
        }
    val name: String by this
    val mentionable: Boolean
        get() {
            return visit<Any, Any>("mentionable").toString().toInt() == 1
        }
    val position: Int by this
    val hoist: Boolean
        get() {
            return visit<Any, Any>("hoist").toString().toInt() == 1
        }

    fun update(t: me.huanmeng.lazykook.entity.Role) {
        copyFrom(t)
    }

    override fun update(data: RoleData) {
    }
}

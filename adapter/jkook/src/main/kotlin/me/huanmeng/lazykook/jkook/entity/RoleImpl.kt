package me.huanmeng.lazykook.jkook.entity

import me.huanmeng.lazykook.entity.Role
import me.huanmeng.lazykook.http.Requests
import me.huanmeng.lazykook.jkook.JGuild
import me.huanmeng.lazykook.jkook.JRole
import snw.jkook.Permission

/**
 * 2024/4/20<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class RoleImpl(val role: Role, guild: Requests.Guild) : JRole {
    override fun getName(): String {
        return role.name
    }

    override fun getGuild(): JGuild {
        TODO("Not yet implemented")
    }

    override fun getId(): Int {
        return role.roleId
    }

    override fun getColor(): Int {
        return role.color
    }

    override fun getPosition(): Int {
        return role.position
    }

    override fun isPermissionSet(permission: Permission?): Boolean {
        TODO("Not yet implemented")
    }

    override fun isMentionable(): Boolean {
        return role.mentionable == 1
    }

    override fun isHoist(): Boolean {
        return role.hoist == 1
    }

    override fun setMentionable(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setHoist(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setPermissions(permValueSum: Int) {
        TODO("Not yet implemented")
    }

    override fun delete() {
        TODO("Not yet implemented")
    }
}
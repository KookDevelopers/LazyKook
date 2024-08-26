package me.huanmeng.lazykook.jkook.entity

import kotlinx.coroutines.runBlocking
import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.jkook.JGuild
import me.huanmeng.lazykook.jkook.JRole
import snw.jkook.Permission

/**
 * 2024/4/20<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class RoleImpl(bot: LazyKook, guildId: String, id: String) : JRole {
    val role by lazy {
        runBlocking {
            bot.storageService.findRole(guildId, id)
        }
    }

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
        return role.mentionable
    }

    override fun isHoist(): Boolean {
        return role.hoist
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
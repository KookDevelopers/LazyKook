package me.huanmeng.lazykook.jkook.entity

import me.huanmeng.lazykook.alive.type.UserData
import me.huanmeng.lazykook.annotation.ByName
import me.huanmeng.lazykook.jkook.JIntimacyInfo
import me.huanmeng.lazykook.jkook.JUser
import snw.jkook.entity.Guild
import snw.jkook.entity.Role
import snw.jkook.entity.channel.VoiceChannel
import snw.jkook.message.PrivateMessage
import snw.jkook.message.component.BaseComponent
import snw.jkook.util.PageIterator

/**
 * 2024/4/20<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class UserImpl(private val user: UserData) : JUser {
    private val vipAvatar: String by user
    private val avatar: String by user
    private val identifyNum: Any by user
    private val status: Any? by user

    @ByName("is_vip")
    private val isVip: Boolean by user

    @ByName("bot")
    private val isBot: Boolean by user
    private val online: Boolean by user

    override fun getName(): String {
        return user.username
    }

    override fun getAvatarUrl(vip: Boolean): String {
        if (vip) {
            return vipAvatar
        }
        return avatar
    }

    override fun getId(): String {
        return user.id
    }

    override fun getNickName(guild: Guild?): String {
        TODO("Not yet implemented")
    }

    override fun getFullName(guild: Guild?): String {
        TODO("Not yet implemented")
    }

    override fun setNickName(guild: Guild?, name: String?) {
        TODO("Not yet implemented")
    }

    override fun getIdentifyNumber(): Int {
        return identifyNum.toString().toInt()
    }

    override fun isVip(): Boolean {
        return isVip
    }

    override fun isBot(): Boolean {
        return isBot
    }

    override fun isOnline(): Boolean {
        return online
    }

    override fun isBanned(): Boolean {
        return status?.toString()?.toInt() == 10
    }

    override fun getRoles(guild: Guild?): MutableCollection<Int> {
        TODO("Not yet implemented")
    }

    override fun sendPrivateMessage(message: String?): String {
        TODO("Not yet implemented")
    }

    override fun sendPrivateMessage(message: String?, quote: PrivateMessage?): String {
        TODO("Not yet implemented")
    }

    override fun sendPrivateMessage(component: BaseComponent?): String {
        TODO("Not yet implemented")
    }

    override fun sendPrivateMessage(component: BaseComponent?, quote: PrivateMessage?): String {
        TODO("Not yet implemented")
    }

    override fun getJoinedVoiceChannel(guild: Guild?): PageIterator<MutableCollection<VoiceChannel>> {
        TODO("Not yet implemented")
    }

    override fun getIntimacy(): Int {
        TODO("Not yet implemented")
    }

    override fun getIntimacyInfo(): JIntimacyInfo {
        TODO("Not yet implemented")
    }

    override fun setIntimacy(intimacy: Int) {
        TODO("Not yet implemented")
    }

    override fun setIntimacy(intimacy: Int, socialInfo: String?, socialImageID: Int?) {
        TODO("Not yet implemented")
    }

    override fun grantRole(role: Role?) {
        TODO("Not yet implemented")
    }

    override fun grantRole(guild: Guild?, roleId: Int) {
        TODO("Not yet implemented")
    }

    override fun revokeRole(role: Role?) {
        TODO("Not yet implemented")
    }

    override fun revokeRole(guild: Guild?, roleId: Int) {
        TODO("Not yet implemented")
    }

    override fun block() {
        TODO("Not yet implemented")
    }

    override fun unblock() {
        TODO("Not yet implemented")
    }
}
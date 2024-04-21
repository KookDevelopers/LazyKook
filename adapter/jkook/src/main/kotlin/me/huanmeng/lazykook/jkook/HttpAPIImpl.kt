package me.huanmeng.lazykook.jkook

import me.huanmeng.lazykook.LazyKook
import snw.jkook.HttpAPI
import snw.jkook.entity.Game
import snw.jkook.entity.Guild
import snw.jkook.entity.User
import snw.jkook.entity.channel.Category
import snw.jkook.entity.channel.Channel
import snw.jkook.message.PrivateMessage
import snw.jkook.message.TextChannelMessage
import snw.jkook.util.PageIterator
import java.io.File

/**
 * 2024/4/20<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class HttpAPIImpl(val lazyKook: LazyKook) : HttpAPI {
    override fun getJoinedGuilds(): PageIterator<MutableCollection<Guild>> {
        TODO("Not yet implemented")
    }

    override fun getUser(p0: String?): User {
        TODO("Not yet implemented")
    }

    override fun getGuild(p0: String?): Guild {
        TODO("Not yet implemented")
    }

    override fun getChannel(p0: String?): Channel {
        TODO("Not yet implemented")
    }

    override fun getCategory(p0: String?): Category {
        TODO("Not yet implemented")
    }

    override fun uploadFile(p0: File?): String {
        TODO("Not yet implemented")
    }

    override fun uploadFile(p0: String?, p1: ByteArray?): String {
        TODO("Not yet implemented")
    }

    override fun uploadFile(p0: String?, p1: String?): String {
        TODO("Not yet implemented")
    }

    override fun removeInvite(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun getGames(): PageIterator<MutableCollection<Game>> {
        TODO("Not yet implemented")
    }

    override fun getGames(p0: Int): PageIterator<MutableCollection<Game>> {
        TODO("Not yet implemented")
    }

    override fun createGame(p0: String?, p1: String?): Game {
        TODO("Not yet implemented")
    }

    override fun setPlaying(p0: Game?) {
        TODO("Not yet implemented")
    }

    override fun setListening(p0: String, p1: String, p2: String) {
        TODO("Not yet implemented")
    }

    override fun stopListening() {
        TODO("Not yet implemented")
    }

    override fun getTextChannelMessage(p0: String?): TextChannelMessage {
        TODO("Not yet implemented")
    }

    override fun getPrivateMessage(p0: User?, p1: String?): PrivateMessage {
        TODO("Not yet implemented")
    }

    override fun getFriendState(p0: Boolean): HttpAPI.FriendState {
        TODO("Not yet implemented")
    }

    override fun addFriend(p0: User?, p1: Int, p2: String?) {
        TODO("Not yet implemented")
    }

    override fun handleFriendRequest(p0: Int, p1: Boolean) {
        TODO("Not yet implemented")
    }

    override fun deleteFriend(p0: User?) {
        TODO("Not yet implemented")
    }
}
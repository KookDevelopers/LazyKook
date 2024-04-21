package me.huanmeng.lazykook.jkook.entity

import me.huanmeng.lazykook.jkook.JReaction
import me.huanmeng.lazykook.signal.event.Reaction
import snw.jkook.entity.CustomEmoji
import snw.jkook.entity.User

/**
 * 2024/4/20<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class ReactionImpl(val reaction: Reaction): JReaction {
    override fun getSender(): User {
        TODO("Not yet implemented")
    }

    override fun getTimeStamp(): Long {
        TODO("Not yet implemented")
    }

    override fun getMessageId(): String {
        TODO("Not yet implemented")
    }

    override fun getEmoji(): CustomEmoji {
        TODO("Not yet implemented")
    }

    override fun delete() {
        TODO("Not yet implemented")
    }
}
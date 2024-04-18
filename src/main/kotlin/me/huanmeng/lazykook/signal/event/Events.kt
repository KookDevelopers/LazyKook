package me.huanmeng.lazykook.signal.event

import com.fasterxml.jackson.annotation.JsonProperty
import me.huanmeng.lazykook.entity.*

// message
sealed class StringMessageExtraData(
    open val type: SignalEventType,
    @JsonProperty("guild_id") open val guildId: String,
    @JsonProperty("channel_name") open val channelName: String,
    open val mention: Array<Any>,
    @JsonProperty("mention_all") open val mentionAll: Boolean,
    @JsonProperty("mention_roles") open val mentionRoles: Array<Any>,
    @JsonProperty("mention_here") open val mentionHere: Boolean,
    open val author: User,
) : SignalEventExtraData()

data class TextMessageExtraData(
    override val type: SignalEventType,
    @JsonProperty("guild_id")
    override val guildId: String,
    @JsonProperty("channel_name")
    override val channelName: String,
    override val mention: Array<Any>,
    @JsonProperty("mention_all")
    override val mentionAll: Boolean,
    @JsonProperty("mention_roles")
    override val mentionRoles: Array<Any>,
    @JsonProperty("mention_here")
    override val mentionHere: Boolean,
    override val author: User,
) : StringMessageExtraData(type, guildId, channelName, mention, mentionAll, mentionRoles, mentionHere, author) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TextMessageExtraData

        if (type != other.type) return false
        if (guildId != other.guildId) return false
        if (channelName != other.channelName) return false
        if (!mention.contentEquals(other.mention)) return false
        if (mentionAll != other.mentionAll) return false
        if (!mentionRoles.contentEquals(other.mentionRoles)) return false
        if (mentionHere != other.mentionHere) return false
        if (author != other.author) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + guildId.hashCode()
        result = 31 * result + channelName.hashCode()
        result = 31 * result + mention.contentHashCode()
        result = 31 * result + mentionAll.hashCode()
        result = 31 * result + mentionRoles.contentHashCode()
        result = 31 * result + mentionHere.hashCode()
        result = 31 * result + author.hashCode()
        return result
    }
}

sealed class ResourceMessageExtraData(
    open val type: SignalEventType,
    open val code: String,
    @JsonProperty("guild_id")
    open val guildId: String,
    open val attachments: Attachments,
    open val author: User
) : SignalEventExtraData()

data class ImageMessageExtraData(
    override val type: SignalEventType,
    override val code: String,
    @JsonProperty("guild_id")
    override val guildId: String,
    override val attachments: Attachments,
    override val author: User
) : ResourceMessageExtraData(type, code, guildId, attachments, author)

data class VideoMessageExtraData(
    override val type: SignalEventType,
    override val code: String,
    @JsonProperty("guild_id")
    override val guildId: String,
    override val attachments: Attachments,
    override val author: User
) : ResourceMessageExtraData(type, code, guildId, attachments, author)

data class FileMessageExtraData(
    override val type: SignalEventType,
    override val code: String,
    @JsonProperty("guild_id")
    override val guildId: String,
    override val attachments: Attachments,
    override val author: User
) : ResourceMessageExtraData(type, code, guildId, attachments, author)

data class KMarkdownMessageExtraData(
    override val type: SignalEventType,
    @JsonProperty("guild_id")
    override val guildId: String,
    @JsonProperty("channel_name")
    override val channelName: String,
    override val mention: Array<Any>,
    @JsonProperty("mention_all")
    override val mentionAll: Boolean,
    @JsonProperty("mention_roles")
    override val mentionRoles: Array<Any>,
    @JsonProperty("mention_here")
    override val mentionHere: Boolean,
    override val author: User,
    @JsonProperty("nav_channels")
    val navChannels: Array<Any>,
    val code: String,
    val kmarkdown: Map<String, Any>
) : StringMessageExtraData(type, guildId, channelName, mention, mentionAll, mentionRoles, mentionHere, author) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KMarkdownMessageExtraData

        if (type != other.type) return false
        if (guildId != other.guildId) return false
        if (channelName != other.channelName) return false
        if (!mention.contentEquals(other.mention)) return false
        if (mentionAll != other.mentionAll) return false
        if (!mentionRoles.contentEquals(other.mentionRoles)) return false
        if (mentionHere != other.mentionHere) return false
        if (author != other.author) return false
        if (!navChannels.contentEquals(other.navChannels)) return false
        if (code != other.code) return false
        if (kmarkdown != other.kmarkdown) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + guildId.hashCode()
        result = 31 * result + channelName.hashCode()
        result = 31 * result + mention.contentHashCode()
        result = 31 * result + mentionAll.hashCode()
        result = 31 * result + mentionRoles.contentHashCode()
        result = 31 * result + mentionHere.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + navChannels.contentHashCode()
        result = 31 * result + code.hashCode()
        result = 31 * result + kmarkdown.hashCode()
        return result
    }
}

data class CardMessageExtraData(
    override val type: SignalEventType,
    @JsonProperty("guild_id")
    override val guildId: String,
    @JsonProperty("channel_name")
    override val channelName: String,
    override val mention: Array<Any>,
    @JsonProperty("mention_all")
    override val mentionAll: Boolean,
    @JsonProperty("mention_roles")
    override val mentionRoles: Array<Any>,
    @JsonProperty("mention_here")
    override val mentionHere: Boolean,
    override val author: User,
    @JsonProperty("nav_channels")
    val navChannels: Array<Any>,
    val code: String,
) : StringMessageExtraData(type, guildId, channelName, mention, mentionAll, mentionRoles, mentionHere, author) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardMessageExtraData

        if (type != other.type) return false
        if (guildId != other.guildId) return false
        if (channelName != other.channelName) return false
        if (!mention.contentEquals(other.mention)) return false
        if (mentionAll != other.mentionAll) return false
        if (!mentionRoles.contentEquals(other.mentionRoles)) return false
        if (mentionHere != other.mentionHere) return false
        if (author != other.author) return false
        if (!navChannels.contentEquals(other.navChannels)) return false
        if (code != other.code) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + guildId.hashCode()
        result = 31 * result + channelName.hashCode()
        result = 31 * result + mention.contentHashCode()
        result = 31 * result + mentionAll.hashCode()
        result = 31 * result + mentionRoles.contentHashCode()
        result = 31 * result + mentionHere.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + navChannels.contentHashCode()
        result = 31 * result + code.hashCode()
        return result
    }
}

data class ItemMessageExtraData(
    val type: SignalEventType,
    val mention: Array<Any>,
    val author: User,
    val kmarkdown: Map<String, Any>
) : SignalEventExtraData()

// system
data class Reaction(
    @JsonProperty("channel_id")
    val channelId: String,
    val emoji: Emoji,
    @JsonProperty("user_id")
    val userId: String,
    @JsonProperty("msg_id")
    val msgId: String,
)

data class AddedReactionExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val reaction: Reaction,
) : SignalEventExtraData()

data class DeletedReactionExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val reaction: Reaction,
) : SignalEventExtraData()

data class UpdateMessage(
    @JsonProperty("channel_id")
    val channelId: String,
    val content: String,
    val mention: Array<Any>,
    @JsonProperty("mention_roles")
    val mentionRoles: Array<Any>,
    @JsonProperty("mention_all")
    val mentionAll: Boolean,
    @JsonProperty("mention_here")
    val mentionHere: Boolean,
    @JsonProperty("update_at")
    val updateAt: Long,
    @JsonProperty("msg_id")
    val msgId: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UpdateMessage

        if (channelId != other.channelId) return false
        if (content != other.content) return false
        if (!mention.contentEquals(other.mention)) return false
        if (!mentionRoles.contentEquals(other.mentionRoles)) return false
        if (mentionAll != other.mentionAll) return false
        if (mentionHere != other.mentionHere) return false
        if (updateAt != other.updateAt) return false
        if (msgId != other.msgId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = channelId.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + mention.contentHashCode()
        result = 31 * result + mentionRoles.contentHashCode()
        result = 31 * result + mentionAll.hashCode()
        result = 31 * result + mentionHere.hashCode()
        result = 31 * result + updateAt.hashCode()
        result = 31 * result + msgId.hashCode()
        return result
    }
}

data class UpdatedMessageExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val message: UpdateMessage,
) : SignalEventExtraData()

data class DeleteMessage(
    @JsonProperty("channel_id")
    val channelId: String,
    @JsonProperty("msg_id")
    val msgId: String,
    val mention: Array<Any>,
    @JsonProperty("mention_roles")
    val mentionRoles: Array<Any>,
    @JsonProperty("mention_all")
    val mentionAll: Boolean,
    @JsonProperty("mention_here")
    val mentionHere: Boolean,
    val content: String?,
    val pin: Int,
    @JsonProperty("pined_time")
    val pinedTime: Long,
    val type: SignalEventType,
    @JsonProperty("created_at")
    val createAt: Long
)

data class DeletedMessageExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val message: DeleteMessage,
) : SignalEventExtraData()

data class AddedChannelExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val channel: Channel,
) : SignalEventExtraData()

data class UpdatedChannelExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val channel: Channel,
) : SignalEventExtraData()

data class DeleteChannel(
    @JsonProperty("channel_id")
    val channelId: String,
    @JsonProperty("deleted_at")
    val deletedAt: Long,
)

data class DeletedChannelExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val channel: DeleteChannel,
) : SignalEventExtraData()

data class PinnedMessage(
    @JsonProperty("channel_id")
    val channelId: String,
    @JsonProperty("operator_id")
    val operatorId: String,
    @JsonProperty("msg_id")
    val msgId: Long,
)

data class PinnedMessageExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val message: PinnedMessage,
) : SignalEventExtraData()

data class UnpinnedMessageExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val message: PinnedMessage,
) : SignalEventExtraData()

// private
data class UpdatePrivateMessage(
    @JsonProperty("author_id")
    val authorId: String,
    @JsonProperty("target_id")
    val targetId: String,
    @JsonProperty("update_at")
    val updateAt: Long,
    @JsonProperty("msg_id")
    val msgId: String,
    val content: String,
    @JsonProperty("chat_code")
    val chatCode: String,
)

data class UpdatedPrivateMessageExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val message: UpdatePrivateMessage,
) : SignalEventExtraData()

data class DeletePrivateMessage(
    @JsonProperty("msg_id")
    val msgId: String,
    @JsonProperty("author_id")
    val authorId: String,
    @JsonProperty("target_id")
    val targetId: String,
    @JsonProperty("chat_code")
    val chatCode: String,
    @JsonProperty("deleted_at")
    val deletedAt: Long,
)

data class DeletedPrivateMessageExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val message: DeletePrivateMessage,
) : SignalEventExtraData()

data class PrivateReaction(
    val emoji: Emoji,
    @JsonProperty("user_id")
    val userId: String,
    @JsonProperty("msg_id")
    val msgId: String,
    @JsonProperty("chat_code")
    val chatCode: String,
)

data class PrivateAddedReactionExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val reaction: PrivateReaction,
) : SignalEventExtraData()

data class PrivateDeletedReactionExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val reaction: PrivateReaction,
) : SignalEventExtraData()

// guild user
data class JoinGuild(
    @JsonProperty("user_id")
    val userId: String,
    @JsonProperty("joined_at")
    val joinedAt: Long,
)

data class JoinedGuildExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val guild: JoinGuild,
) : SignalEventExtraData()

data class ExitGuild(
    @JsonProperty("user_id")
    val userId: String,
    @JsonProperty("exited_at")
    val exitedAt: Long,
)

data class ExitedGuildExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val guild: ExitGuild,
) : SignalEventExtraData()

data class UpdateGuildMember(
    @JsonProperty("user_id")
    val userId: String,
    val nickname: String,
)

data class UpdatedGuildMemberExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val member: UpdateGuildMember,
) : SignalEventExtraData()

data class GuildMemberOnline(
    @JsonProperty("user_id")
    val userId: String,
    @JsonProperty("event_time")
    val onlineAt: Long,
    val guilds: List<String>,
)

data class GuildMemberOnlineExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val member: GuildMemberOnline,
) : SignalEventExtraData()

data class GuildMemberOffline(
    @JsonProperty("user_id")
    val userId: String,
    @JsonProperty("event_time")
    val offlineAt: Long,
    val guilds: List<String>,
)

data class GuildMemberOfflineExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val member: GuildMemberOffline,
) : SignalEventExtraData()

// role
data class AddedRoleExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val role: Role,
) : SignalEventExtraData()

data class DeletedRoleExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val role: Role,
) : SignalEventExtraData()

data class UpdatedRoleExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val role: Role,
) : SignalEventExtraData()

// guild
data class UpdatedGuildExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val guild: GuidInfo,
) : SignalEventExtraData()

data class DeletedGuildExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val guild: GuidInfo,
) : SignalEventExtraData()

data class AddBlockList(
    @JsonProperty("user_id")
    val userIds: List<String>,
    @JsonProperty("operator_id")
    val operatorId: String,
    val remark: String,
)

data class AddedBlockListExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val blockList: AddBlockList,
) : SignalEventExtraData()

data class DeleteBlockList(
    @JsonProperty("user_id")
    val userIds: List<String>,
    @JsonProperty("operator_id")
    val operatorId: String,
)

data class DeletedBlockListExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val blockList: DeleteBlockList,
) : SignalEventExtraData()

data class AddedEmojiExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val emoji: Emoji,
) : SignalEventExtraData()

data class RemovedEmojiExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val emoji: Emoji,
) : SignalEventExtraData()

data class UpdatedEmojiExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val emoji: Emoji,
) : SignalEventExtraData()

// user
data class JoinedChannel(
    @JsonProperty("user_id")
    val userId: String,
    @JsonProperty("joined_at")
    val joinedAt: Long,
    @JsonProperty("channel_id")
    val channelId: String,
)

data class JoinedChannelExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val channel: JoinedChannel,
) : SignalEventExtraData()

data class ExitedChannel(
    @JsonProperty("user_id")
    val userId: String,
    @JsonProperty("exited_at")
    val exitedAt: Long,
    @JsonProperty("channel_id")
    val channelId: String,
)

data class ExitedChannelExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val channel: ExitedChannel,
) : SignalEventExtraData()

data class UserUpdatedExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val user: UserInfo,
) : SignalEventExtraData()

data class SelfJoinGuild(
    @JsonProperty("guild_id")
    val guildId: String,
)

data class SelfJoinedGuildExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val guild: SelfJoinGuild,
) : SignalEventExtraData()

data class SelfExitedGuild(
    @JsonProperty("guild_id")
    val guildId: String,
)

data class SelfExitedGuildExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val guild: SelfExitedGuild,
) : SignalEventExtraData()

data class MessageBtnClick(
    @JsonProperty("msg_id")
    val msgId: String,
    @JsonProperty("user_id")
    val userId: String,
    val value: String,
    @JsonProperty("target_id")
    val targetId: String,
    @JsonProperty("user_info")
    val user: User
)

data class MessageBtnClickExtraData(
    val type: SignalSystemEventType,
    @JsonProperty("body")
    val message: MessageBtnClick,
) : SignalEventExtraData()
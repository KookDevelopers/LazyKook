package me.huanmeng.lazykook.signal.event

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import me.huanmeng.lazykook.entity.ChannelType
import me.huanmeng.lazykook.mapper
import me.huanmeng.lazykook.signal.SignalEventDeserializer

/**
 * 2024/4/18<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
@JsonDeserialize(using = SignalEventDeserializer::class)
data class SignalData(
    @JsonProperty("channel_type")
    val channelType: ChannelType,
    val type: SignalEventType,
    @JsonProperty("target_id") val targetId: String,
    @JsonProperty("author_id") val authorId: String,
    val content: Any,
    @JsonProperty("msg_id") val msgId: String,
    @JsonProperty("msg_timestamp") val msgTimestamp: Long,
    val nonce: String,
    val extra: SignalExtraData,
) {
    val text by lazy {
        if (content is String) {
            content
        } else ""
    }
    val itemContent by lazy {
        if (content !is String) {
            mapper.readValue(mapper.writeValueAsString(content), ItemEventContent::class.java)
        } else null
    }
}

sealed class SignalExtraData

enum class SignalEventType(@JsonValue val value: Int, val clazz: Class<out SignalExtraData>? = null) {
    TEXT(1, TextMessageExtraData::class.java),
    IMAGE(2, ImageMessageExtraData::class.java),
    VIDEO(3, VideoMessageExtraData::class.java),
    FILE(4, FileMessageExtraData::class.java),
    AUDIO(8),
    KMARKDOWN(9, KMarkdownMessageExtraData::class.java),
    CARD(10, CardMessageExtraData::class.java),
    ITEM(12, ItemMessageExtraData::class.java),
    SYSTEM(255),
}

data class ItemEventContent(
    @JsonProperty("user_id")
    val userId: String,
    @JsonProperty("target_id")
    val targetId: String,
    @JsonProperty("item_id")
    val itemId: Int,
)

enum class SignalSystemEventType(@JsonValue val value: String, val clazz: Class<out SignalExtraData>) {
    // channel
    ADDED_REACTION("added_reaction", AddedReactionExtraData::class.java),
    DELETED_REACTION("deleted_reaction", DeletedReactionExtraData::class.java),
    UPDATED_MESSAGE("updated_message", UpdatedMessageExtraData::class.java),
    DELETED_MESSAGE("deleted_message", DeletedMessageExtraData::class.java),
    ADDED_CHANNEL("added_channel", AddedChannelExtraData::class.java),
    UPDATED_CHANNEL("updated_channel", UpdatedChannelExtraData::class.java),
    DELETED_CHANNEL("deleted_channel", DeletedChannelExtraData::class.java),
    PINNED_MESSAGE("pinned_message", PinnedMessageExtraData::class.java),
    UNPINNED_MESSAGE("unpinned_message", UnpinnedMessageExtraData::class.java),

    // private
    UPDATED_PRIVATE_MESSAGE("updated_private_message", UpdatedPrivateMessageExtraData::class.java),
    DELETED_PRIVATE_MESSAGE("deleted_private_message", DeletedPrivateMessageExtraData::class.java),
    PRIVATE_ADDED_REACTION("private_added_reaction", PrivateAddedReactionExtraData::class.java),
    PRIVATE_DELETED_REACTION("private_deleted_reaction", PrivateDeletedReactionExtraData::class.java),

    // guild user
    JOINED_GUILD("joined_guild", JoinedGuildExtraData::class.java),
    EXITED_GUILD("exited_guild", ExitedGuildExtraData::class.java),
    UPDATED_GUILD_MEMBER("updated_guild_member", UpdatedGuildMemberExtraData::class.java),
    GUILD_MEMBER_ONLINE("guild_member_online", GuildMemberOnlineExtraData::class.java),
    GUILD_MEMBER_OFFLINE("guild_member_offline", GuildMemberOfflineExtraData::class.java),

    // role
    ADDED_ROLE("added_role", AddedRoleExtraData::class.java),
    DELETED_ROLE("deleted_role", DeletedRoleExtraData::class.java),
    UPDATED_ROLE("updated_role", UpdatedRoleExtraData::class.java),

    // guild
    UPDATED_GUILD("updated_guild", UpdatedGuildExtraData::class.java),
    DELETED_GUILD("deleted_guild", DeletedGuildExtraData::class.java),
    ADDED_BLOCK_LIST("added_block_list", DeletedBlockListExtraData::class.java),
    DELETED_BLOCK_LIST("deleted_block_list", DeletedBlockListExtraData::class.java),
    ADDED_EMOJI("added_emoji", AddedEmojiExtraData::class.java),
    REMOVED_EMOJI("removed_emoji", RemovedEmojiExtraData::class.java),
    UPDATED_EMOJI("updated_emoji", UpdatedEmojiExtraData::class.java),

    // user
    JOINED_CHANNEL("joined_channel", JoinedChannelExtraData::class.java),
    EXITED_CHANNEL("exited_channel", ExitedChannelExtraData::class.java),
    USER_UPDATED("user_updated", UserUpdatedExtraData::class.java),
    SELF_JOINED_GUILD("self_joined_guild", SelfJoinedGuildExtraData::class.java),
    SELF_EXITED_GUILD("self_exited_guild", SelfExitedGuildExtraData::class.java),

    // card
    MESSAGE_BTN_CLICK("message_btn_click", MessageBtnClickExtraData::class.java)
}
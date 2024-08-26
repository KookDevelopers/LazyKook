package me.huanmeng.lazykook.http.request

import com.fasterxml.jackson.annotation.JsonProperty
import me.huanmeng.lazykook.enums.badge.BadgeStyle
import me.huanmeng.lazykook.enums.oauth.GrantType
import me.huanmeng.lazykook.signal.event.SignalEventType

annotation class Comment(val value: String)

data class GatewayRequest(
    val compress: Int = 1
)

data class PageRequest(
    val page: Int = 1,
    @JsonProperty("page_size")
    val pageSize: Int = 20,
    val sort: String? = null
)

data class GuildViewRequest(
    @JsonProperty("guild_id")
    val guildId: String
)

data class GuildUserListRequest(
    @JsonProperty("guild_id")
    val guildId: String,
    @JsonProperty("channel_id")
    val channelId: String? = null,
    val search: String? = null,
    @JsonProperty("role_id")
    val roleId: String? = null,
    @JsonProperty("mobile_verified")
    val mobileVerified: Int? = null,
    @JsonProperty("active_time")
    val activeTime: Int? = null,
    @JsonProperty("joined_at")
    val joinedAt: Long? = null,
    val page: Int = 1,
    val pageSize: Int = 20,
    @JsonProperty("filter_user_id")
    val filterUserId: String? = null,
)

data class GuildNicknameRequest(
    @JsonProperty("guild_id")
    val guildId: String,
    @JsonProperty("user_id")
    val userId: String? = null,
    val nickname: String? = null,
)

data class GuildLeaveRequest(
    @JsonProperty("guild_id")
    val guildId: String
)

data class GuildKickRequest(
    @JsonProperty("guild_id")
    val guildId: String,
    @JsonProperty("target_id")
    val userId: String,
)

data class GuildMuteListRequest(
    @JsonProperty("guild_id")
    val guildId: String,
    @JsonProperty("return_type")
    val returnType: String? = null,// sug: detail
)

data class GuildMuteCreateRequest(
    @JsonProperty("guild_id")
    val guildId: String,
    @JsonProperty("user_id")
    val userId: String,
    val type: Int,
)

data class GuildMuteDeleteRequest(
    @JsonProperty("guild_id")
    val guildId: String,
    @JsonProperty("user_id")
    val userId: String,
    val type: Int,
)

data class GuildBoostHistoryRequest(
    @JsonProperty("guild_id")
    val guildId: String,
    @JsonProperty("start_time")
    @Comment("unix")
    val startTime: Int,
    @JsonProperty("end_time")
    @Comment("unix")
    val endTime: Int,
)

// user
data class UserViewRequest(
    @JsonProperty("user_id")
    val userId: String,
    @JsonProperty("guild_id")
    val guildId: String? = null,
)

// role
data class RoleListRequest(
    @JsonProperty("guild_id")
    val guildId: String,
    val page: Int = 1,
    @JsonProperty("page_size")
    val pageSize: Int = 20,
)

// channel
data class ChannelViewRequest(
    @JsonProperty("target_id")
    val channelId: String,
    @JsonProperty("need_children")
    val needChildren: Boolean = false
)

// message
data class MessageCreateRequest(
    @JsonProperty("target_id")
    val targetId: String,
    val content: String,
    @JsonProperty("quote")
    val quote: String? = null,
    @JsonProperty("temp_target_id")
    val tempTargetId: String? = null,
    val nonce: String? = null,
    val type: Int = 1,
)

data class MessageUpdateRequest(
    @JsonProperty("target_id")
    val targetId: String,
    val content: String,
    @JsonProperty("quote")
    val quote: String? = null,
    @JsonProperty("temp_target_id")
    val tempTargetId: String? = null,
)

data class MessageDeleteRequest(
    @JsonProperty("msg_id")
    val msgId: String,
)

data class MessageViewRequest(
    @JsonProperty("msg_id")
    val msgId: String,
)

// channel-user
data class JoinedChannelRequest(
    @JsonProperty("guild_id")
    val guildId: String,
    @JsonProperty("user_id")
    val userId: String,
    val page: Int = 1,
    @JsonProperty("page_size")
    val pageSize: Int = 20,
)

// direct-message
data class DirectMessageCreateRequest(
    val type: SignalEventType = SignalEventType.KMARKDOWN,
    @JsonProperty("target_id")
    val userId: String?,
    val content: String,
    val quote: String? = null,
    val nonce: String? = null,
    @JsonProperty("chat_code")
    val chatCode: String? = null
)

data class DirectMessageViewRequest(
    @JsonProperty("chat_code")
    val chatCode: String,
    @JsonProperty("msg_id")
    val msgId: String
)

// badge
data class BadgeGuildRequest(
    @JsonProperty("guild_id")
    val guildId: String,
    val style: BadgeStyle
)

// oauth
data class OAuthTokenRequest(
    @JsonProperty("grant_type")
    val grantType: GrantType,
    @JsonProperty("client_id")
    val clientId: String,
    @JsonProperty("client_secret")
    val clientSecret: String,
    val code: String,
    @JsonProperty("redirect_uri")
    val redirectUri: String,
)

// voice
data class VoiceJoinRequest(
    @JsonProperty("channel_id")
    val channelId: String,
    @JsonProperty("audio_ssrc")
    val audioSsrc: String? = null,
    @JsonProperty("audio_pt")
    val audioPt: String? = null,
    @JsonProperty("rtcp_mux")
    val rtcpMux: Boolean? = null,
    val password: String? = null,
)

data class VoiceLeaveRequest(
    @JsonProperty("channel_id")
    val channelId: String,
)

data class VoiceKeepAliveRequest(
    @JsonProperty("channel_id")
    val channelId: String,
)
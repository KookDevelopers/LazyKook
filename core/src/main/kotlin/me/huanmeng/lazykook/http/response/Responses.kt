package me.huanmeng.lazykook.http.response

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import me.huanmeng.lazykook.entity.*
import me.huanmeng.lazykook.enums.oauth.TokenType
import me.huanmeng.lazykook.locateValue
import kotlin.reflect.KProperty


data class RootResponse(
    val code: Int,
    val message: String,
    val data: Any,
)

data class GatewayResponse(
    val url: String,
)

open class PageResponse<T>(
    open val items: List<T>, open val meta: PageMeta, open val sort: Map<String, Int>? = null
)

data class PageMeta(
    val page: Int,
    @JsonProperty("page_total") val pageTotal: Int,
    @JsonProperty("page_size") val pageSize: Int,
    val total: Int,
)

data class GuildListResponse(
    override val items: List<GuildInfo>,
    override val meta: PageMeta,
    override val sort: Map<String, Int>? = null
) : PageResponse<GuildInfo>(items, meta, sort)

data class GuildViewResponse(
    val roles: List<Role>,
    val channels: List<Channel>,
    val id: String,
    val name: String,
    val topic: String,
    @JsonProperty("master_id") val masterId: String,
    @JsonProperty("user_id") val userId: String,
    @JsonProperty("is_master") val isMaster: Boolean,
    val icon: String,
    @JsonProperty("notify_type") val notifyType: NotifyType,
    val region: String,
    @JsonProperty("enable_open") val enableOpen: Boolean,
    @JsonProperty("open_id") val openId: String,
    @JsonProperty("default_channel_id") val defaultChannelId: String,
    @JsonProperty("welcome_channel_id") val welcomeChannelId: String,
    val features: List<Any> = emptyList(),
    val boostNum: Int,
    val bufferBoostNum: Int,
    val level: Int,
    val status: Int,
    @JsonProperty("auto_delete_time") val autoDeleteTime: String,
    @JsonProperty("user_config") val userConfig: Map<String, Any>?,
    @field:JsonAnySetter
    @get:JsonAnyGetter
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) {
    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}

data class MuteData(
    val type: Int,
    val userIds: List<String>,
)

data class GuildMuteListResponse(
    val mic: MuteData,
    val headset: MuteData,
    @field:JsonAnySetter
    @get:JsonAnyGetter
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) {
    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}

data class GuildBoostHistory(
    @JsonProperty("user_id") val userId: String,
    @JsonProperty("guild_id") val guildId: String,
    @JsonProperty("start_time") val startTime: Int,
    @JsonProperty("end_time") val endTime: Int,
    val user: User,
    @field:JsonAnySetter @get:JsonAnyGetter val unknownField: MutableMap<String, Any?> = hashMapOf()
) {
    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}

data class GuildBoostHistoryResponse(
    override val items: List<GuildBoostHistory>,
    override val meta: PageMeta,
    override val sort: Map<String, Int>? = null
) : PageResponse<GuildBoostHistory>(items, meta, sort)

data class GuildUserListResponse(
    override val items: List<User>,
    override val meta: PageMeta,
    override val sort: Map<String, Int>? = null,
    @JsonProperty("user_count") val userCount: Int,
    @JsonProperty("online_count") val onlineCount: Int,
    @JsonProperty("offline_count") val offlineCount: Int,
    @field:JsonAnySetter
    @get:JsonAnyGetter
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) : PageResponse<User>(items, meta, sort) {
    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}

data class RoleListResponse(
    override val items: List<Role>,
    override val meta: PageMeta,
    override val sort: Map<String, Int>? = null,
    @field:JsonAnySetter
    @get:JsonAnyGetter
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) : PageResponse<Role>(items, meta, sort) {
    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}

// message
data class MessageCreateResponse(
    @JsonProperty("msg_id") val msgId: String,
    @JsonProperty("msg_timestamp") val msgTimestamp: Long,
    val nonce: String,
    @field:JsonAnySetter
    @get:JsonAnyGetter
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) {
    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}

// channel-user
data class JoinedChannelResponse(
    override val items: List<Channel>,
    override val meta: PageMeta,
    override val sort: Map<String, Int>? = null
) : PageResponse<Channel>(items, meta, sort)

// direct-message
/*data class DirectMessageViewResponse(

)*/

// badge
data class BadgeSvg(val content: String)

// oauth
data class OAuthTokenResponse(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("expire_in")
    val expireInSec: Long,
    @JsonProperty("token_type")
    val tokenType: TokenType,
    /**
     * 空格分割
     */
    val scope: String
)

// voice
data class VoiceJoinResponse(
    val ip: String,
    val port: String,
    val rtcp_mux: Boolean,
    val rtcp_port: Int,
    val bitrate: Int,
    val audio_ssrc: String,
    val audio_pt: String,
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) {
    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}

data class VoiceChannelData(
    @JsonProperty("guild_id")
    val guildId: String,
    @JsonProperty("id")
    val channelId: String,
    val name: String,
    @JsonProperty("parent_id")
    val parentChannelId: String
)

data class VoiceListResponse(
    override val items: List<VoiceChannelData>,
    override val meta: PageMeta,
    override val sort: Map<String, Int>? = null
) : PageResponse<VoiceChannelData>(items, meta, sort)
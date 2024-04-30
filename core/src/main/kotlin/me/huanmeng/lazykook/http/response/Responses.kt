package me.huanmeng.lazykook.http.response

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import me.huanmeng.lazykook.entity.*
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
    override val items: List<GuildInfo>, override val meta: PageMeta, override val sort: Map<String, Int>? = null
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
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) {
    @JsonAnySetter
    fun setUnknownField(key: String, value: Any?) {
        unknownField[key] = value
    }

    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}

data class MuteData(
    val type: Int,
    val userIds: List<String>,
)

data class GuildMuteListResponse(
    val mic: MuteData, val headset: MuteData, val unknownField: MutableMap<String, Any?> = hashMapOf()
) {
    @JsonAnySetter
    fun setUnknownField(key: String, value: Any?) {
        unknownField[key] = value
    }

    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}

data class GuildBoostHistory(
    @JsonProperty("user_id") val userId: String,
    @JsonProperty("guild_id") val guildId: String,
    @JsonProperty("start_time") val startTime: Int,
    @JsonProperty("end_time") val endTime: Int,
    val user: User,
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) {
    @JsonAnySetter
    fun setUnknownField(key: String, value: Any?) {
        unknownField[key] = value
    }

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
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) : PageResponse<User>(items, meta, sort) {
    @JsonAnySetter
    fun setUnknownField(key: String, value: Any?) {
        unknownField[key] = value
    }

    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}

data class RoleListResponse(
    override val items: List<Role>,
    override val meta: PageMeta,
    override val sort: Map<String, Int>? = null,
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) : PageResponse<Role>(items, meta, sort) {
    @JsonAnySetter
    fun setUnknownField(key: String, value: Any?) {
        unknownField[key] = value
    }

    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}

// message
data class MessageCreateResponse(
    @JsonProperty("msg_id")
    val msgId: String,
    @JsonProperty("msg_timestamp")
    val msgTimestamp: Long,
    val nonce: String,
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) {
    @JsonAnySetter
    fun setUnknownField(key: String, value: Any?) {
        unknownField[key] = value
    }

    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}
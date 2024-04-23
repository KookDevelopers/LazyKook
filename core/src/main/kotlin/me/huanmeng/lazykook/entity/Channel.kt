package me.huanmeng.lazykook.entity

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import me.huanmeng.lazykook.locateValue
import kotlin.reflect.KProperty

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class Channel(
    val id: String,
    val name: String,
    @JsonProperty("user_id") val userId: String,
    @JsonProperty("guild_id") val guildId: String,
    val topic: String,
    @JsonProperty("is_category") val isCategory: Boolean,
    @JsonProperty("parent_id") val parentId: String,
    val level: Int,
    @JsonProperty("slow_mode") val slowMode: Int,
    val type: Int,
    @JsonProperty("last_msg_content")
    val lastMsgContent: String,
    @JsonProperty("last_msg_id")
    val lastMsgId: String,
    @JsonProperty("permission_overwrites") val permissionOverwrites: List<PermissionOverwrite>,
    @JsonProperty("permission_users") val permissionUsers: List<PermissionUser>,
    @JsonProperty("permission_sync") val permissionSync: Int,
    @JsonProperty("has_password") val hasPassword: Boolean,
    @JsonProperty("limit_amount")
    val limitAmount: Int,
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) {
    @JsonAnySetter
    fun setUnknownField(key: String, value: Any?) {
        unknownField[key] = value
    }

    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}

data class PermissionOverwrite(
    @JsonProperty("role_id") val roleId: Int,
    val allow: Int,
    val deny: Int,
)

data class PermissionUser(
    val user: User,
    val allow: Int,
    val deny: Int,
)
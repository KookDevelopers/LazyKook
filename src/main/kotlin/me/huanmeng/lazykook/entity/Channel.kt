package me.huanmeng.lazykook.entity

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class Channel(
    val id: String,
    val name: String,
    @JsonProperty("user_id") val userId: Int,
    @JsonProperty("guild_id") val guildId: String,
    val topic: String,
    @JsonProperty("is_category") val isCategory: Boolean,
    @JsonProperty("parent_id") val parentId: String,
    val level: Int,
    @JsonProperty("slow_mode") val slowMode: Int,
    val type: Int,
    @JsonProperty("permission_overwrites") val permissionOverwrites: List<PermissionOverwrite>,
    @JsonProperty("permission_users") val permissionUsers: List<PermissionUser>,
    @JsonProperty("permission_sync") val permissionSync: Int,
    @JsonProperty("has_password") val hasPassword: Boolean,
)

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
package me.huanmeng.lazykook.entity

import com.squareup.moshi.Json

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class Channel(
    val id: String,
    val name: String,
    @Json(name = "user_id")
    val userId: Int,
    @Json(name = "guild_id")
    val guildId: String,
    val topic: String,
    @Json(name = "is_category")
    val isCategory: Boolean,
    @Json(name = "parent_id")
    val parentId: String,
    val level: Int,
    @Json(name = "slow_mode")
    val slowMode: Int,
    val type: Int,
    @Json(name = "permission_overwrites")
    val permissionOverwrites: List<PermissionOverwrite>,
    @Json(name = "permission_users")
    val permissionUsers: List<PermissionUser>,
    @Json(name = "permission_sync")
    val permissionSync: Int,
    @Json(name = "has_password")
    val hasPassword: Boolean,
)

data class PermissionOverwrite(
    @Json(name = "role_id")
    val roleId: Int,
    val allow: Int,
    val deny: Int,
)

data class PermissionUser(
    val user: User,
    val allow: Int,
    val deny: Int,
)
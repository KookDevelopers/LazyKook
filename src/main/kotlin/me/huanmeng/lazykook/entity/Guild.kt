package me.huanmeng.lazykook.entity

import com.squareup.moshi.Json

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class Guild(
    val id: String,
    val name: String,
    val topic: String,
    @Json(name = "user_id")
    val userId: String,
    val icon: String,
    @Json(name = "notify_type")
    val notifyType: Int,
    val region: String,
    @Json(name = "enable_open")
    val enableOpen: Boolean,
    @Json(name = "open_id")
    val openId: String,
    @Json(name = "default_channel_id")
    val defaultChannelId: String,
    @Json(name = "welcome_channel_id")
    val welcomeChannelId: String,
    val roles: List<Role>,
    val channels: List<Channel>,
) {
}
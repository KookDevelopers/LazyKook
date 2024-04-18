package me.huanmeng.lazykook.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class GuidInfo(
    val id: String,
    val name: String,
    @JsonProperty("user_id")
    val userId: String,
    val icon: String,
    @JsonProperty("notify_type")
    val notifyType: NotifyType,
    val region: String,
    @JsonProperty("enable_open")
    val enableOpen: Int,
    @JsonProperty("open_id")
    val openId: Int,
    @JsonProperty("default_channel_id")
    val defaultChannelId: String,
    @JsonProperty("welcome_channel_id")
    val welcomeChannelId: String,
) {
    enum class NotifyType(@JsonValue val value: Int) {
        DEFAULT(0),
        ALL(1),
        MENTION(2),
        DISABLE(3)
    }
}

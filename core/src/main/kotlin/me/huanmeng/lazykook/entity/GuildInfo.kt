package me.huanmeng.lazykook.entity

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import me.huanmeng.lazykook.locateValue
import kotlin.reflect.KProperty

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class GuildInfo(
    val id: String,
    val name: String,
    val topic: String,
    @JsonProperty("master_id")
    val masterId: String,
    @JsonProperty("user_id")
    val userId: String,
    val icon: String,
    @JsonProperty("notify_type")
    val notifyType: NotifyType,
    val region: String,
    @JsonProperty("enable_open")
    val enableOpen: Boolean,
    @JsonProperty("open_id")
    val openId: Int,
    @JsonProperty("default_channel_id")
    val defaultChannelId: String,
    @JsonProperty("welcome_channel_id")
    val welcomeChannelId: String,
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) {
    @JsonAnySetter
    fun setUnknownField(key: String, value: Any?) {
        unknownField[key] = value
    }

    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}

enum class NotifyType(@JsonValue val value: Int) {
    DEFAULT(0),
    ALL(1),
    MENTION(2),
    DISABLE(3)
}

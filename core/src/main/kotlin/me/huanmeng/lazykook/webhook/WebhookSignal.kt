package me.huanmeng.lazykook.webhook

import com.fasterxml.jackson.annotation.JsonProperty
import me.huanmeng.lazykook.entity.ChannelType
import me.huanmeng.lazykook.signal.event.SignalEventType

/**
 * 2024/5/2<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class WebhookSignalData(
    val type: SignalEventType,
    @JsonProperty("channel_type")
    val channelType: ChannelType,
    val challenge: String,
    @JsonProperty("verify_token")
    val verifyToken: String,
)
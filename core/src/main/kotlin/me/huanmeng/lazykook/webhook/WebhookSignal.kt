package me.huanmeng.lazykook.webhook

import com.fasterxml.jackson.annotation.JsonProperty
import me.huanmeng.lazykook.enums.EventType
import me.huanmeng.lazykook.signal.event.SignalEventType

/**
 * 2024/5/2<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class WebhookSignalData(
    val type: SignalEventType,
    @JsonProperty("channel_type")
    val eventType: EventType,
    val challenge: String,
    @JsonProperty("verify_token")
    val verifyToken: String,
)
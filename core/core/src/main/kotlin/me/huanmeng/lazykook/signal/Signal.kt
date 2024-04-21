package me.huanmeng.lazykook.signal

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class Signal(
    @JsonProperty("s")
    val type: Int,
    @JsonProperty("d")
    @JsonDeserialize(using = SignalExtraDeserializer::class)
    val data: Any? = null,
    val extra: Map<String, Any>? = null,
    @JsonProperty("sn")
    val signal: Int = -99,
)

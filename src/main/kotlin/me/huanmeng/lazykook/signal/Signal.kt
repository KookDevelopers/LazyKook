package me.huanmeng.lazykook.signal

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class Signal(
    @JsonProperty("s")
    val type: Int,
    @JsonProperty("d")
    val data: Map<String, Any>? = null,
    @JsonProperty("sn")
    val signal: Int = -99,
)

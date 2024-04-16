package me.huanmeng.lazykook.signal

import com.squareup.moshi.Json

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class Signal(
    @Json(name = "s")
    val type: Int,
    @Json(name = "d")
    val data: Map<String, Any>? = null,
    @Json(name = "sn")
    val signal: Int = -99,
)

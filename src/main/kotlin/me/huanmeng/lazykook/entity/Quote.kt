package me.huanmeng.lazykook.entity

import com.squareup.moshi.Json

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class Quote(
    val id: String,
    val type: Int,
    val content: String,
    @Json(name = "create_at")
    val createAt: Long,
    val author: User,
)

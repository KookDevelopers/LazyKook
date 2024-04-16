package me.huanmeng.lazykook.entity

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class Quote(
    val id: String,
    val type: Int,
    val content: String,
    @JsonProperty("create_at") val createAt: Long,
    val author: User,
)

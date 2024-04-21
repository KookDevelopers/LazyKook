package me.huanmeng.lazykook.entity

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 2024/4/18<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class UserInfo(
    @JsonProperty("user_id") val userId: String,
    val username: String,
    val avatar: String,
    val unknownField: MutableMap<String, Any> = hashMapOf()
) {
    @JsonAnySetter
    fun setUnknownField(key: String, value: Any) {
        unknownField[key] = value
    }
}
package me.huanmeng.lazykook.entity

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class Role(
    @JsonProperty("role_id")
    val roleId: Int,
    val name: String,
    val color: Int,
    val position: Int,
    val hoist: Int,
    val mentionable: Int,
    val permissions: Int,
    val unknownField: MutableMap<String, Any> = hashMapOf()
) {
    @JsonAnySetter
    fun setUnknownField(key: String, value: Any) {
        unknownField[key] = value
    }
}

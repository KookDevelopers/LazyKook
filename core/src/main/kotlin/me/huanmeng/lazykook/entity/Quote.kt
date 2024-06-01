package me.huanmeng.lazykook.entity

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import me.huanmeng.lazykook.locateValue
import kotlin.reflect.KProperty

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
    @field:JsonAnySetter
    @get:JsonAnyGetter
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) {
    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}
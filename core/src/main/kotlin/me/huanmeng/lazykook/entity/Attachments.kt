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
data class Attachments(
    val type: String,
    val url: String,
    val name: String,
    // video/file event
    @JsonProperty("file_type")
    val fileType: String?,
    val size: Int?,
    val duration: Int?,
    val width: Int?,
    val height: Int?,
    @field:JsonAnySetter
    @get:JsonAnyGetter
    val unknownField: MutableMap<String, Any?> = hashMapOf()
) {
    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(unknownField, property)
}

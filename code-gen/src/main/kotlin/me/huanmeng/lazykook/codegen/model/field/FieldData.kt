package me.huanmeng.lazykook.codegen.model.field

import com.fasterxml.jackson.annotation.JsonProperty
import me.huanmeng.lazykook.codegen.Type

/**
 * 2024/6/28<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class FieldData(
    val type: Type,
    @JsonProperty("field")
    val jsonFieldName: String? = null,
    val supportTypes: List<Type> = emptyList(),
    @JsonProperty("id")
    val isId: Boolean = false,
    @JsonProperty("alias")
    val isAlias: Boolean = false,
    val proxy: String? = null,
)

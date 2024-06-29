package me.huanmeng.lazykook.codegen.model

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import me.huanmeng.lazykook.codegen.model.extra.ClassExtraFieldData
import me.huanmeng.lazykook.codegen.model.field.FieldData
import me.huanmeng.lazykook.codegen.model.method.MethodData

/**
 * 2024/6/28<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class ClassData(
    @JsonProperty("class")
    val className: String,
    @field:JsonAnySetter
    @get:JsonAnyGetter
    val fields: Map<String, FieldData> = hashMapOf()
) {
    var extraFields: Map<String, ClassExtraFieldData> = emptyMap()
    var methods: List<MethodData> = emptyList()
    fun gen(writer: StringBuilder) {
        fields.forEach { (fieldName, fieldData) ->
            if (fieldName == "id" || fieldData.isId) {
                return@forEach
            }
            writer.append("\tval $fieldName: ${fieldData.type.text} ")
            if (fieldData.supportTypes.isEmpty()) {
                writer.appendLine("by this")
            } else {
                writer.appendLine()
                writer.appendLine("\t\tget() {")
                val type = fieldData.supportTypes.first { it.canConvertTo(fieldData.type) }
                writer.append("\t\t\treturn ")
                writer.append("visit<Any, Any>(\"${fieldData.jsonFieldName ?: fieldName}\")")
                type.convertTo(fieldData.type, writer)
                writer.appendLine()
                writer.appendLine("\t\t}")
            }
        }
        extraFields.forEach { name, field ->
            field.write(writer, name)
        }

        methods.forEach {
            it.write(writer)
        }
    }
}

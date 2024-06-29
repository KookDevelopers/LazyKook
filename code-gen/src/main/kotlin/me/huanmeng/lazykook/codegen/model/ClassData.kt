package me.huanmeng.lazykook.codegen.model

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter

/**
 * 2024/6/28<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class ClassData(
    @field:JsonAnySetter
    @get:JsonAnyGetter
    val fields: Map<String, FieldData> = hashMapOf()
) {
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
    }
}

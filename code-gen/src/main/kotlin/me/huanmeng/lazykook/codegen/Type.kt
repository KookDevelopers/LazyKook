package me.huanmeng.lazykook.codegen

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * 2024/6/28<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
@JsonDeserialize(using = TypeDeserializer::class)
data class Type(val text: String) {
    val convertMap: MutableMap<Type, (StringBuilder.() -> Unit)> = hashMapOf()
    override fun toString(): String {
        return text
    }

    fun canConvertTo(type: Type): Boolean {
        return convertMap.containsKey(type)
    }

    fun convertTo(target: Type, sb: StringBuilder) {
        convertMap[target]?.also {
            sb.it()
        }
    }

    fun addConvertTo(target: Type, invoke: (StringBuilder.() -> Unit)) {
        convertMap[target] = invoke
    }

    companion object {
        val INT = Type("Int")
        val STRING = Type("String")
        val BOOLEAN = Type("Boolean")
        val LONG = Type("Long")
        fun getType(name: String): Type {
            if (name.startsWith("array:")) {
                val arrayType = name.substring(6)
                return Type("Array<${getType(arrayType).text}>")
            }
            return when (name) {
                "int" -> INT
                "string" -> STRING
                "bool", "boolean" -> BOOLEAN
                "long" -> LONG
                else -> throw IllegalArgumentException("Type $name not supported")
            }
        }

        init {
            INT.addConvertTo(STRING) {
                append(".toString()")
            }
            STRING.addConvertTo(INT) {
                append(".toString().toInt()")
            }
            INT.addConvertTo(BOOLEAN) {
                append(".toString().toInt() == 1")
            }
        }
    }
}

class TypeDeserializer : JsonDeserializer<Type>() {
    override fun deserialize(parser: JsonParser, context: DeserializationContext): Type {
        val node = parser.codec.readTree<JsonNode>(parser)
        return Type.getType(node.asText())
    }

}
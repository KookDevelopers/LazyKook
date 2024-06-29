package me.huanmeng.lazykook.codegen


import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import me.huanmeng.lazykook.codegen.model.ClassData
import me.huanmeng.lazykook.codegen.model.extra.ClassExtraFieldData
import me.huanmeng.lazykook.codegen.model.method.ConfiguredMethodData
import java.io.File

/**
 * 2024/6/15<br></br>
 * LazyKook<br></br>
 *
 * @author huanmeng_qwq
 */
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val dest = if (args.isNotEmpty()) args[0] else ""
        if (dest.isBlank()) {
            println("<dest:Path>")
            return
        }
        val folder = File(dest)
        val mapper = jacksonObjectMapper()
        val registryInputStream = javaClass.getResourceAsStream("/registry.json")
        val registry = mapper.readTree(registryInputStream)
        write(registry, mapper, folder)
    }

    private fun write(
        registry: JsonNode,
        mapper: ObjectMapper,
        dest: File,
    ) {
        registry.get("fields").forEach { namespace ->
            val name = namespace.asText()
            val fieldInputStream = javaClass.getResourceAsStream("/fields/$name.json")
            val classData = mapper.readValue(fieldInputStream, ClassData::class.java)
            registry.get("methods")?.get(name)?.asText()?.also {
                val methodInputStream = javaClass.getResourceAsStream("/methods/$it.json")
                val methodData = mapper.readValue(methodInputStream, ConfiguredMethodData::class.java)
                classData.methods = methodData.toList()
            }
            registry.get("extra")?.get(name)?.asText()?.also {
                val extraInputStream = javaClass.getResourceAsStream("/extra/$it.json")
                val fieldDataList =
                    mapper.readValue(extraInputStream, jacksonTypeRef<Map<String, ClassExtraFieldData>>())
                classData.extraFields = fieldDataList
            }
            val className = classData.className
            val classBuilder = StringBuilder()
            doc(classBuilder)
            classBuilder.appendLine("package me.huanmeng.lazykook.alive.type")
            classBuilder.appendLine()
            classBuilder.appendLine("import me.huanmeng.lazykook.alive.AliveData")
            classBuilder.appendLine()
            val fieldData =
                classData.fields.firstNotNullOfOrNull { if (it.value.isId || it.key == "id") it else null }
            classBuilder.appendLine("class ${className}(id: String) : AliveData<${className}>(\"${fieldData?.value?.jsonFieldName ?: fieldData?.key ?: "unknown"}\", id) {")
            classData.gen(classBuilder)
            classBuilder.appendLine()
            classBuilder.appendLine("\toverride fun update(data: ${className}) {")
            classBuilder.appendLine("\t}")
            classBuilder.appendLine("}")
            dest.resolve("${className}.kt").writeText(classBuilder.toString())
        }
    }

    fun doc(sb: StringBuilder) {
        sb.appendLine("/**")
        sb.appendLine("* This file is generated automatically, please do not modify it")
        sb.appendLine("*/")
    }
}

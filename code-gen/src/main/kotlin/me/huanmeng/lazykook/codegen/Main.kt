package me.huanmeng.lazykook.codegen


import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import me.huanmeng.lazykook.codegen.model.ClassData
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
        val mapper = jacksonObjectMapper()
        val file = File(dest)
        val node = mapper.readTree(file)
        val classData = mapper.readValue(file, ClassData::class.java)
        val packageName = "me.huanmeng.lazykook.alive.type"
        val className = "TestData"
        val classBuilder = StringBuilder()
        doc(classBuilder)
        classBuilder.appendLine("package $packageName")
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
        println(classBuilder)
    }

    /**
     * 2024/6/28<br>
     * LazyKook<br>
     * @author huanmeng_qwq
     */
    fun doc(sb: StringBuilder) {
        sb.appendLine("/**")
        sb.appendLine("* This file is generated automatically, please do not modify it")
        sb.appendLine("*/")
    }
}

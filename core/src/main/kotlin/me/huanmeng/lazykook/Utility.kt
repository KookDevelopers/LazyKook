package me.huanmeng.lazykook

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import me.huanmeng.lazykook.annotation.ByName
import java.io.ByteArrayOutputStream
import java.util.zip.Inflater
import kotlin.reflect.KProperty

fun <K, V> Map<K, V>.toPairs(): Array<Pair<K, V>> {
    return this.entries.map { it.toPair() }.toTypedArray()
}

val mapper = jacksonObjectMapper()

fun ByteArray.uncompress(): ByteArray {
    Inflater().let { inf ->
        inf.reset()
        inf.setInput(this)
        ByteArrayOutputStream(size).use {
            val buf = ByteArray(1024)
            while (!inf.finished()) {
                val i = inf.inflate(buf)
                it.write(buf, 0, i)
            }
            inf.end()
            return it.toByteArray()
        }
    }
}

fun <V, V1 : V> locateValue(
    map: Map<String, Any?>, property: KProperty<*>, nameGetter: ((KProperty<*>) -> String?)? = null
): V1 {
    val name = nameGetter?.invoke(property) ?: property.annotations.filter { it.annotationClass == ByName::class }
        .map { it as ByName }.firstOrNull()?.value ?: property.name
    @Suppress("UNCHECKED_CAST") return (map[name] as V1)
}
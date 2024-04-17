package me.huanmeng.lazykook

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.ByteArrayOutputStream
import java.util.zip.Inflater

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
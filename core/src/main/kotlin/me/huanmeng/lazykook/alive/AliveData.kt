package me.huanmeng.lazykook.alive

import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

/**
 * 2024/4/20<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
abstract class AliveData<T> {
    private val map: MutableMap<String, Any> = hashMapOf()
    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 =
        @Suppress("UNCHECKED_CAST") (map[property.name] as V1)

    abstract fun id(): String
    fun keys(): Set<String> {
        return map.keys
    }

    fun update(key: String, value: Any?): Boolean {
        if (value == null) {
            return map.remove(key) != null
        }
        return map.put(key, value) != null
    }

    fun update(full: Map<String, Any>) {
        map.putAll(full)
    }

    abstract fun update(data: T)
}

fun <V> dynamic(value: V) = object : ObservableProperty<V>(value) {}

fun <V> dynamicNull() = object : ObservableProperty<V?>(null) {}
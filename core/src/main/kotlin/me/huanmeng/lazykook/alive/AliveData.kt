package me.huanmeng.lazykook.alive

import me.huanmeng.lazykook.locateValue
import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

/**
 * 2024/4/20<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
abstract class AliveData<T>(private val key: String, id: String) {
    private val map: MutableMap<String, Any> = hashMapOf()
    operator fun <V, V1 : V> getValue(thisRef: Any?, property: KProperty<*>): V1 = locateValue(map, property)

    fun <V, V1 : V> visit(name: String): V1 {
        @Suppress("UNCHECKED_CAST")
        return map[name] as V1
    }

    init {
        update(key, id)
    }

    val id: String
        get() {
            return map[key].toString()
        }

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

    protected operator fun set(key: String, value: Any?) {
        update(key, value)
    }

    protected fun copyFrom(any: Any) {
        any::class.java.declaredFields.forEach {
            try {
                it.trySetAccessible()
                if (it.name == "unknownField" && it.type.isAssignableFrom(Map::class.java)) {
                    val map = it.get(any) as Map<*, *>
                    map.forEach { (key, value) ->
                        update(key.toString(), value)
                    }
                } else {
                    update(it.name, it.get(any))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun toString(): String {
        return javaClass.simpleName + "($map)"
    }

}

fun <V> dynamic(value: V) = object : ObservableProperty<V>(value) {}

fun <V> dynamicNull() = object : ObservableProperty<V?>(null) {}
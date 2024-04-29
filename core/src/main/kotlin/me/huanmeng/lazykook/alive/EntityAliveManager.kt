package me.huanmeng.lazykook.alive

import me.huanmeng.lazykook.alive.type.GuildData
import me.huanmeng.lazykook.annotation.NotRecommended
import java.util.*

class GuildManager : EntityAliveManager<GuildData, GuildData>("guild")

abstract class EntityAliveManager<V, T : AliveData<V>>(private val name: String) {
    private val aliveMap: MutableMap<UUID, T> = mutableMapOf()

    fun alive(aliveData: T): T {
        aliveMap[getUUID(aliveData.id)] = aliveData
        return aliveData
    }

    fun getOrNull(id: String): T? {
        return aliveMap[getUUID(id)]
    }

    fun <Y : T> getOrAlive(id: String, alive: () -> Y): Y {
        @Suppress("UNCHECKED_CAST")
        return aliveMap.getOrPut(getUUID(id), alive) as Y
    }


    private fun getUUID(id: String): UUID {
        return UUID.nameUUIDFromBytes("Name: $name Id: $id".toByteArray())
    }

    fun unAlive(aliveData: T) {
        aliveMap.remove(getUUID(aliveData.id))
    }

    fun update(aliveData: T, value: V) {
        aliveMap[getUUID(aliveData.id)]?.update(value)
    }

    fun update(aliveData: T, key: String, value: Any?) {
        aliveMap[getUUID(aliveData.id)]?.update(key, value)
    }

    operator fun plusAssign(aliveData: T) {
        alive(aliveData)
    }

    operator fun get(id: String): T? {
        return getOrNull(id)
    }

    operator fun set(id: String, value: V) {
        getOrNull(id)?.also {
            update(it, value)
        }
    }

    operator fun set(id: String, key: String, value: Any?) {
        getOrNull(id)?.also {
            update(it, key, value)
        }
    }

    @NotRecommended
    operator fun set(id: String, full: Map<String, Any>) {
        getOrNull(id)?.also {
            it.update(full)
        }
    }
}
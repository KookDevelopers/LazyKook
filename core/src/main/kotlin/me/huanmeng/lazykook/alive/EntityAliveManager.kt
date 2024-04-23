package me.huanmeng.lazykook.alive

import me.huanmeng.lazykook.alive.type.GuildData
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

    fun update(aliveData: T, full: Map<String, Any>) {
        aliveMap[getUUID(aliveData.id)]?.update(full)
    }
}
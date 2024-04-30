package me.huanmeng.lazykook.alive

import me.huanmeng.lazykook.alive.type.ChannelData
import me.huanmeng.lazykook.alive.type.GuildData
import me.huanmeng.lazykook.alive.type.RoleData
import me.huanmeng.lazykook.alive.type.UserData
import me.huanmeng.lazykook.annotation.NotRecommended
import java.util.*

class GuildManager : EntityAliveManager<GuildData, GuildData>("guild")
class UserManager : EntityAliveManager<UserData, UserData>("user")
class RoleManager : EntityAliveManager<RoleData, RoleData>("role")
class ChannelManager : EntityAliveManager<ChannelData, ChannelData>("channel")

abstract class EntityAliveManager<V, T : AliveData<V>>(private val name: String) {
    private val aliveMap: MutableMap<UUID, T> = mutableMapOf()

    fun alive(aliveData: T): T {
        aliveMap[getUuid(aliveData.id)] = aliveData
        return aliveData
    }

    fun getOrNull(id: String): T? {
        return aliveMap[getUuid(id)]
    }

    fun <Y : T> getOrAlive(id: String, alive: () -> Y): Y {
        @Suppress("UNCHECKED_CAST")
        return aliveMap.getOrPut(getUuid(id), alive) as Y
    }


    private fun getUuid(id: String): UUID {
        return UUID.nameUUIDFromBytes("Name: $name Id: $id".toByteArray())
    }

    fun unAlive(aliveData: T) {
        aliveMap.remove(getUuid(aliveData.id))
    }

    fun update(aliveData: T, value: V) {
        aliveMap[getUuid(aliveData.id)]?.update(value)
    }

    fun update(aliveData: T, key: String, value: Any?) {
        aliveMap[getUuid(aliveData.id)]?.update(key, value)
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
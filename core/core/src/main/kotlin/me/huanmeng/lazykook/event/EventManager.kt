package me.huanmeng.lazykook.event

import me.huanmeng.event.AbstractEventManager
import org.slf4j.LoggerFactory

/**
 * 2024/4/18<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class EventManager : AbstractEventManager<Event, Listener, EventProcessor>(
    Event::class.java, Listener::class.java, EventProcessor::class.java, LoggerFactory.getLogger("EventManager")
) {
    fun registerListener(listener: Listener) {
        register(listener)
    }

    fun <E : Event> postEvent(event: E): E {
        callEvent(event)
        return event
    }

    override fun onRegister(listener: Listener) {
    }

    override fun onUnregister(listener: Listener) {
    }
}
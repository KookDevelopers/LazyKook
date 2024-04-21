package me.huanmeng.lazykook.event

import net.kyori.event.Cancellable

/**
 * 2024/4/18<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
interface Event

class CancelEvent : Cancellable {
    private var cancelled = false
    override fun cancelled(): Boolean {
        return cancelled
    }

    override fun cancelled(cancelled: Boolean) {
        this.cancelled = cancelled
    }

}
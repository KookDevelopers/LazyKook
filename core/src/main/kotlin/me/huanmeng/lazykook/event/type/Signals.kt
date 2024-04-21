package me.huanmeng.lazykook.event.type

import me.huanmeng.lazykook.event.Event
import me.huanmeng.lazykook.signal.Signal
import me.huanmeng.lazykook.signal.event.SignalData
import me.huanmeng.lazykook.signal.event.SignalExtraData

data class ReceiverSignalEvent(val signal: Signal) : Event
data class SignalExtraDataEvent(
    val signal: Signal, val signalExtra: SignalData, val data: SignalExtraData
) : Event

data class SignalExtraEvent(
    val signal: Signal,
    val extra: SignalData
) : Event

data class SignalHelloEvent(val signal: Signal) : Event

data class SignalPongEvent(val signal: Signal) : Event

object SignalPingEvent : Event
package me.huanmeng.lazykook.enums

import com.fasterxml.jackson.annotation.JsonValue

/**
 * 2024/7/4<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
enum class ChannelType(@JsonValue val value: Int) {
    TEXT(1),VOICE(2),
}
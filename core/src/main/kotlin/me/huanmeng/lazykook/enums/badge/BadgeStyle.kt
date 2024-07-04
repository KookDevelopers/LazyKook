package me.huanmeng.lazykook.enums.badge

import com.fasterxml.jackson.annotation.JsonValue

/**
 * 2024/7/4<br>
 * LazyKook-parent<br>
 * @author huanmeng_qwq
 */
enum class BadgeStyle(@JsonValue val value: Int) {
    SERVER_NAME(0),
    ONLINE(1),
    ONLINE_TOTAL(2),
}
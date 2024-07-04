package me.huanmeng.lazykook.enums.oauth

import com.fasterxml.jackson.annotation.JsonValue

/**
 * 2024/7/4<br>
 * LazyKook-parent<br>
 * @author huanmeng_qwq
 */
enum class GrantType(@JsonValue val type: String) {
    AUTHORIZATION_CODE("authorization_code")
}
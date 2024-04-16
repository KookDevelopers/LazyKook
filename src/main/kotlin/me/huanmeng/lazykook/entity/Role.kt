package me.huanmeng.lazykook.entity

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class Role(
    val role_id: Int,
    val name: String,
    val color: Int,
    val position: Int,
    val hoist: Int,
    val mentionable: Int,
    val permissions: Int,
)

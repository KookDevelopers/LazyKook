package me.huanmeng.lazykook.entity

import com.squareup.moshi.Json

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class User(
    val id: String,
    val username: String,
    val nickname: String,
    @Json(name = "identify_num")
    val identifyNum: String,
    val online: Boolean,
    val bot: Boolean,
    val status: Int,
    val avatar: String,
    @Json(name = "vip_avatar")
    val vipAvatar: String,
    @Json(name = "mobile_verified")
    val mobileVerified: Boolean,
    val roles: List<Int>,
) {
}
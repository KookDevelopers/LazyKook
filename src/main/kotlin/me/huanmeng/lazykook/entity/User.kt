package me.huanmeng.lazykook.entity

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class User(
    val id: String,
    val username: String,
    val nickname: String,
    @JsonProperty("identify_num")
    val identifyNum: String,
    val online: Boolean,
    val bot: Boolean,
    val status: Int,
    val avatar: String,
    @JsonProperty("vip_avatar")
    val vipAvatar: String,
    @JsonProperty("mobile_verified")
    val mobileVerified: Boolean,
    val roles: List<Int>,
) {
}
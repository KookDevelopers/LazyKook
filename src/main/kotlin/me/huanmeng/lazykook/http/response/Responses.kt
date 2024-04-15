package me.huanmeng.lazykook.http.response


data class RootResponse(
    val code: Int,
    val message: String,
    val data: Any,
)

data class GatewayResponse(
    val url: String,
)
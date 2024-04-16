package me.huanmeng.lazykook.http

import me.huanmeng.lazykook.http.request.GatewayRequest
import me.huanmeng.lazykook.http.response.GatewayResponse

/**
 * 2024/4/15<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class APIRouter<REQ : Class<*>, RESP : Class<*>>(
    @get:JvmName("path")
    val path: String,
    @get:JvmName("requestClass")
    val requestClass: REQ,
    @get:JvmName("responseClass")
    val responseClass: RESP,
    @get:JvmName("version")
    val version: APIVersion = APIVersion.V3,
    @get:JvmName("apiMethod")
    val apiMethod: HttpMethod = HttpMethod.POST
) {
    fun buildPath(): String {
        return version.versionPath + path
    }
}

enum class APIVersion(@get:JvmName("versionPath") val versionPath: String) {
    V3("/v3")
}

enum class HttpMethod {
    GET, POST
}

object Requests {
    val GATEWAY =
        APIRouter("/gateway/index", GatewayRequest::class.java, GatewayResponse::class.java, apiMethod = HttpMethod.GET)
}
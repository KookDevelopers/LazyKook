package me.huanmeng.lazykook.http

import com.fasterxml.jackson.core.type.TypeReference
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.config.HttpConfig
import me.huanmeng.lazykook.exception.HttpException
import me.huanmeng.lazykook.http.HttpMethod.GET
import me.huanmeng.lazykook.http.HttpMethod.POST
import me.huanmeng.lazykook.http.response.RootResponse
import me.huanmeng.lazykook.mapper
import me.huanmeng.lazykook.toPairs

/**
 * 2024/4/15<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class KHttp(private val kook: LazyKook) {
    private val config: HttpConfig
        get() = kook.config.httpConfig
    private val client = HttpClient(CIO) {
        install(io.ktor.client.plugins.HttpTimeout) {
            requestTimeoutMillis = config.timeout
        }
    }

    private suspend fun <T> get(
        path: String,
        vararg params: Pair<String, Any?>,
        callback: (suspend HttpResponse.() -> T)
    ): T {
        return client.get(config.url + path) {
            params.forEach {
                if (it.second == null) return@forEach
                parameter(it.first, it.second.toString())
            }
            token()
        }.callback()
    }

    private suspend fun <T> post(
        path: String,
        vararg params: Pair<String, Any?>,
        callback: (suspend HttpResponse.() -> T)
    ): T {
        return client.submitForm(config.url + path) {
            token()
            setBody(
                FormDataContent(
                    Parameters.build {
                        params.forEach {
                            if (it.second == null) return@forEach
                            append(it.first, it.second.toString())
                        }
                    }
                )
            )
        }.callback()
    }

    suspend fun <REQ : Any, RESP : Any> http(
        apiRouter: APIRouter<Class<REQ>, Class<RESP>>,
        request: REQ?,
        respCallback: ((String) -> Unit) = {}
    ): RESP {
        val map = if (!apiRouter.requestIsNull)
            mapper.readValue(mapper.writeValueAsString(request), object : TypeReference<Map<String, Any?>>() {})
        else emptyMap()

        val responseObject = when (apiRouter.apiMethod) {
            GET -> get(apiRouter.buildPath(), *map.toPairs()) {
                apiRouter.responseHandler.invoke(this)
            }

            POST -> post(apiRouter.buildPath(), *map.toPairs()) {
                apiRouter.responseHandler.invoke(this)
            }
        }
        if (responseObject !is String) {
            if (apiRouter.responseClass.isInstance(responseObject)) {
                @Suppress("UNCHECKED_CAST")
                return responseObject as RESP
            }
            throw HttpException("Error Object: $responseObject")
        }
        val response =
            mapper.readValue(responseObject, RootResponse::class.java) ?: throw RuntimeException(responseObject)

        val dataJson = mapper.writeValueAsString(response.data)
        respCallback(dataJson)
        return try {
            mapper.readValue(dataJson, apiRouter.responseClass)
        } catch (e: Exception) {
            // {"code":401,"message":"系统检测到您的登录环境异常，为保证您的账号安全，请重新登录","data":{"name":"Unauthorized","status":401}}
            // {"code":500,"message":"系统错误","data":{"name":"Internal Server Error","status":500}}
            throw HttpException(responseObject + "${kook.config}", e)
        }
    }

    private fun HttpRequestBuilder.token(): HttpRequestBuilder {
        header(HttpHeaders.UserAgent, "huanmeng-qwq/LazyKook")
        header(HttpHeaders.Authorization, "Bot ${kook.config.token}")
        return this
    }
}
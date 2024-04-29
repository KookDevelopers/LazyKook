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

    private suspend fun get(path: String, vararg params: Pair<String, Any?>): String {
        return client.get(config.url + path) {
            params.forEach {
                if (it.second == null) return@forEach
                parameter(it.first, it.second.toString())
            }
            token()
        }.bodyAsText()
    }

    private suspend fun post(path: String, vararg params: Pair<String, Any?>): String {
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
        }.bodyAsText()
    }

    suspend fun <REQ : Any, RESP : Any> http(
        apiRouter: APIRouter<Class<REQ>, Class<RESP>>,
        request: REQ,
        respCallback: ((String) -> Unit) = {}
    ): RESP {
        val map =
            mapper.readValue(mapper.writeValueAsString(request), object : TypeReference<Map<String, Any?>>() {})

        val responseJson = when (apiRouter.apiMethod) {
            GET -> this::get
            POST -> this::post
        }(apiRouter.buildPath(), map.toPairs())
        val response = mapper.readValue(responseJson, RootResponse::class.java) ?: throw RuntimeException(responseJson)

        val dataJson = mapper.writeValueAsString(response.data)
        respCallback(dataJson)
        return try {
            mapper.readValue(dataJson, apiRouter.responseClass)
        } catch (e: Exception) {
            // eg {"code":401,"message":"系统检测到您的登录环境异常，为保证您的账号安全，请重新登录","data":{"name":"Unauthorized","status":401}}
            throw HttpException(responseJson)
        }
    }

    private fun HttpRequestBuilder.token(): HttpRequestBuilder {
        header(HttpHeaders.UserAgent, "huanmeng-qwq/LazyKook")
        header(HttpHeaders.Authorization, "Bot ${kook.config.token}")
        return this
    }
}
package me.huanmeng.lazykook.http

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    private val rootResponseAdapter = moshi.adapter(RootResponse::class.java)
    private val stringAdapter = moshi.adapter(Any::class.java)

    private suspend fun get(path: String, vararg params: Pair<String, Any>): String {
        return client.get(config.url + path) {
            params.forEach {
                parameter(it.first, it.second.toString())
            }
            token()
        }.bodyAsText()
    }

    private suspend fun post(path: String, vararg params: Pair<String, Any>): String {
        return client.submitForm(config.url + path) {
            token()
            setBody(
                FormDataContent(
                    Parameters.build {
                        params.forEach { append(it.first, it.second.toString()) }
                    }
                )
            )
        }.bodyAsText()
    }

    suspend fun <REQ : Any, RESP : Any> http(apiRouter: APIRouter<Class<REQ>, Class<RESP>>, request: REQ): RESP {
        val reqAdapter = moshi.adapter(apiRouter.requestClass)
        val respAdapter = moshi.adapter(apiRouter.responseClass)
        val params = reqAdapter.toJsonValue(request)

        @Suppress("UNCHECKED_CAST") val map = params as MutableMap<String, Any>
        val responseJson = when (apiRouter.apiMethod) {
            GET -> this::get
            POST -> this::post
        }(apiRouter.buildPath(), map.toPairs())
        val response = rootResponseAdapter.fromJson(responseJson) ?: throw RuntimeException(responseJson)

        val dataJson = stringAdapter.toJson(response.data)
        return respAdapter.fromJson(dataJson) ?: throw HttpException(dataJson)
    }

    private fun HttpRequestBuilder.token(): HttpRequestBuilder {
        header(HttpHeaders.UserAgent, "huanmeng-qwq/LazyKook")
        header(HttpHeaders.Authorization, "Bot ${kook.config.token}")
        return this
    }
}
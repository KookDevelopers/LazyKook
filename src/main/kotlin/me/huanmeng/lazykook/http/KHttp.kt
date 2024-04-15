package me.huanmeng.lazykook.http

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.exception.HttpException
import me.huanmeng.lazykook.http.HttpMethod.GET
import me.huanmeng.lazykook.http.HttpMethod.POST
import me.huanmeng.lazykook.http.response.RootResponse
import me.huanmeng.lazykook.toPairs
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * 2024/4/15<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
@OptIn(ExperimentalStdlibApi::class)
class KHttp(private val kook: LazyKook) {
    private val client = OkHttpClient.Builder().build()
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val rootResponseAdapter = moshi.adapter(RootResponse::class.java)
    private val stringAdapter = moshi.adapter(Any::class.java)

    private fun get(path: String, vararg params: Pair<String, Any>): String {
        return client.newCall(
            Request.Builder().get().url(kook.config.httpConfig.url + path.let {
                if (params.isEmpty()) {
                    it
                } else {
                    it + "?" + params.joinToString("&") { (k, v) -> "$k=$v" }
                }
            }).token().build()
        ).execute().body.let {
            if (it == null) {
                throw RuntimeException("Unsupported")
            }
            it
        }.string()
    }

    private fun post(path: String, vararg params: Pair<String, Any>): String {
        return client.newCall(Request.Builder().post(FormBody.Builder().apply {
            params.forEach {
                add(it.first, it.second.toString())
            }
        }.build()).url(kook.config.httpConfig.url + path).token().build()).execute().body.let {
            if (it == null) {
                throw RuntimeException("Unsupported")
            }
            it
        }.string()
    }

    fun <REQ : Any, RESP : Any> http(apiRouter: APIRouter<Class<REQ>, Class<RESP>>, request: REQ): RESP {
        val reqAdapter = moshi.adapter(apiRouter.requestClass)
        val respAdapter = moshi.adapter(apiRouter.responseClass)
        val params = reqAdapter.toJsonValue(request)

        @Suppress("UNCHECKED_CAST")
        val map = params as MutableMap<String, Any>
        val responseJson = when (apiRouter.apiMethod) {
            GET -> {
                get(apiRouter.buildPath(), *map.toPairs())
            }

            POST -> {
                post(apiRouter.buildPath(), *map.toPairs())
            }
        }
        val response = rootResponseAdapter.fromJson(responseJson)
            ?: throw RuntimeException(responseJson)

        val dataJson = stringAdapter.toJson(response.data)
        return respAdapter.fromJson(dataJson) ?: throw HttpException(dataJson)
    }

    private fun Request.Builder.token(): Request.Builder {
        return this.header("Authorization", "Bot ${kook.config.token}")
    }
}
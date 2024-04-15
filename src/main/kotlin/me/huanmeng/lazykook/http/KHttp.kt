package me.huanmeng.lazykook.http

import me.huanmeng.lazykook.LazyKook
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * 2024/4/15<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class KHttp(val kook: LazyKook) {
    val client = OkHttpClient.Builder().build()

    private fun get(url: String): String {
        return client.newCall(Request.Builder().get().url(url).token().build()).execute().body!!.string()
    }

    private fun post(url: String, vararg params: Pair<String, Any>): String {
        return client.newCall(Request.Builder().post(FormBody.Builder()
            .apply {
                params.forEach {
                    add(it.first, it.second.toString())
                }
            }
            .build()).url(url).token().build()).execute().body!!.string()
    }

    private fun Request.Builder.token(): Request.Builder {
        return this.header("Authorization", "Bot ${kook.config.token}")
    }
}
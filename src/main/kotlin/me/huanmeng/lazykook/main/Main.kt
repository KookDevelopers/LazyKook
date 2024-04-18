package me.huanmeng.lazykook.main

import kotlinx.coroutines.runBlocking
import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.config.BotConfig
import me.huanmeng.lazykook.http.Requests
import me.huanmeng.lazykook.http.request.GuildUserListRequest
import me.huanmeng.lazykook.http.request.UserViewRequest
import snw.jkook.config.file.YamlConfiguration
import java.io.File

/**
 * 2024/4/15<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
object Main {
    // TEST
    @JvmStatic
    fun main(args: Array<String>) {
        val cfg = YamlConfiguration.loadConfiguration(File("config.yml"))
        val tk = cfg.getString("token")!!
        val bot = LazyKook(BotConfig(tk))
        runBlocking {
            bot.http.apply {
                println(
                    http(Requests.Guild.USER_LIST, GuildUserListRequest("1112094556602289")) {
                        println(it)
                    }
                )
                println(http(Requests.User.VIEW, UserViewRequest("3672631392")) {
                    println(it)
                })
            }
//            bot.start()
        }
    }
}
package me.huanmeng.lazykook.main

import kotlinx.coroutines.runBlocking
import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.config.BotConfig
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
            bot.start()
        }
    }
}
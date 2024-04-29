package me.huanmeng.lazykook.main

import kotlinx.coroutines.runBlocking
import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.config.BotConfig
import org.bspfsystems.yamlconfiguration.file.YamlConfiguration
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
            val guild = bot.storageService.findGuild("2530876875583713") ?: return@runBlocking
            println(guild.guildId)

            val user = bot.storageService.findUser("3672631392", "2530876875583713") ?: return@runBlocking
            println(user)
            println(user.getNicknames())
        }
    }
}
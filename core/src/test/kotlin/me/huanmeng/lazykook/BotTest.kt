package me.huanmeng.lazykook

import me.huanmeng.lazykook.config.BotConfig
import org.bspfsystems.yamlconfiguration.file.YamlConfiguration
import java.io.File

/**
 * 2024/4/30<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
open class BotTest {
    val testDir = File("run").also {
        if (!it.exists()) {
            it.mkdirs()
        }
    }
    val bot: LazyKook

    init {
        val file = File(testDir, "config.yml").also {
            if (!it.exists()) {
                it.writeText("token: 'please input your token'")
            }
        }
        val cfg = YamlConfiguration.loadConfiguration(file)
        val tk = cfg.getString("token")!!
        bot = LazyKook(BotConfig(tk))
    }

    companion object {
        const val testGuildId = "2530876875583713"
        const val testUserId = "3672631392"
    }
}
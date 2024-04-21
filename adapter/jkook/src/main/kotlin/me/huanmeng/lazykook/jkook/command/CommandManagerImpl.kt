package me.huanmeng.lazykook.jkook.command

import snw.jkook.command.CommandManager
import snw.jkook.command.CommandSender
import snw.jkook.command.JKookCommand
import snw.jkook.message.Message
import snw.jkook.plugin.Plugin
import java.util.function.Function
import java.util.function.Supplier

/**
 * 2024/4/20<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class CommandManagerImpl: CommandManager {
    override fun registerCommand(p0: Plugin?, p1: JKookCommand?) {
        TODO("Not yet implemented")
    }

    override fun registerCommand(p0: Plugin?, p1: Supplier<JKookCommand>?) {
        TODO("Not yet implemented")
    }

    override fun executeCommand(p0: CommandSender?, p1: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun executeCommand(p0: CommandSender?, p1: String?, p2: Message?): Boolean {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> registerArgumentParser(p0: Class<T>?, p1: Function<String, T>?) {
        TODO("Not yet implemented")
    }
}
package me.huanmeng.lazykook.jkook

import me.huanmeng.lazykook.LazyKook
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import snw.jkook.Core
import snw.jkook.HttpAPI
import snw.jkook.Unsafe
import snw.jkook.command.CommandManager
import snw.jkook.command.ConsoleCommandSender
import snw.jkook.entity.User
import snw.jkook.event.EventManager
import snw.jkook.plugin.PluginManager
import snw.jkook.scheduler.Scheduler

/**
 * 2024/4/20<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class CoreImpl(val lazyKook: LazyKook) : Core {
    private val _logger = LoggerFactory.getLogger("LazyKook")
    override fun getHttpAPI(): HttpAPI {
        TODO("Not yet implemented")
    }

    override fun getAPIVersion(): String = "0.50.0"

    override fun getImplementationName(): String = "LazyKook"

    override fun getImplementationVersion(): String = "1.0.0"

    override fun getScheduler(): Scheduler {
        TODO("Not yet implemented")
    }

    override fun getEventManager(): EventManager {
        TODO("Not yet implemented")
    }

    override fun getLogger(): Logger {
        return _logger
    }

    override fun getConsoleCommandSender(): ConsoleCommandSender {
        TODO("Not yet implemented")
    }

    override fun getCommandManager(): CommandManager {
        TODO("Not yet implemented")
    }

    override fun getPluginManager(): PluginManager {
        TODO("Not yet implemented")
    }

    override fun getUser(): User {
        TODO("Not yet implemented")
    }

    override fun setUser(p0: User?) {
        TODO("Not yet implemented")
    }

    override fun getUnsafe(): Unsafe {
        TODO("Not yet implemented")
    }

    override fun shutdown() {
        TODO("Not yet implemented")
    }
}
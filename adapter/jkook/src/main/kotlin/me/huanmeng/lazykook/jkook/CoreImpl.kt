package me.huanmeng.lazykook.jkook

import kotlinx.coroutines.runBlocking
import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.jkook.command.CommandManagerImpl
import me.huanmeng.lazykook.jkook.entity.UserImpl
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
    private val _httpAPI by lazy { HttpAPIImpl(lazyKook) }
    private val _commandManager by lazy {
        CommandManagerImpl()
    }
    private val _me by lazy {
        runBlocking {
            UserImpl(lazyKook.storageService.findMe())
        }
    }

    override fun getHttpAPI(): HttpAPI {
        return _httpAPI
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
        return _commandManager
    }

    override fun getPluginManager(): PluginManager {
        TODO("Not yet implemented")
    }

    override fun getUser(): User {
        return _me
    }

    override fun setUser(p0: User?) {
        error("Not yet implemented")
    }

    override fun getUnsafe(): Unsafe {
        TODO("Not yet implemented")
    }

    override fun shutdown() {
        lazyKook.stop()
        TODO("Not yet implemented")
    }
}
package me.huanmeng.lazykook.dsl

import me.huanmeng.lazykook.LazyKook
import me.huanmeng.lazykook.config.BotConfig

fun bot(config: BotConfig, init: LazyKook.() -> Unit): LazyKook = LazyKook(config).also(init)

fun bot(token: String, init: LazyKook.() -> Unit) = bot(BotConfig(token), init)

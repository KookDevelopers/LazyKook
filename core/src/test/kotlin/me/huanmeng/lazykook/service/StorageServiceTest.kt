package me.huanmeng.lazykook.service

import kotlinx.coroutines.runBlocking
import me.huanmeng.lazykook.BotTest
import org.junit.jupiter.api.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * 2024/4/30<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class StorageServiceTest : BotTest() {
    val service = bot.storageService

    @Test
    fun `test findGuild`() = runBlocking {
        val guild = service.findGuild(testGuildId)
        assertNotNull(guild)
        assertEquals(testGuildId, guild.id)
    }

    @Test
    fun `test findUser`() = runBlocking {
        val user = service.findUser(testUserId, testGuildId)
        assertNotNull(user)
        assertEquals(testUserId, user.id)
        assertContains(user.getNicknames(), testGuildId)
    }
}
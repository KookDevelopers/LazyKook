package me.huanmeng.lazykook.service

import kotlinx.coroutines.runBlocking
import me.huanmeng.lazykook.BotTest
import org.junit.jupiter.api.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

/**
 * 2024/4/30<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class StorageServiceTest : BotTest() {
    private val service = bot.storageService

    @Test
    fun `test findGuild`() = runBlocking {
        val guild = service.findGuild(testGuildId)
        assertEquals(testGuildId, guild.guildId)
    }

    @Test
    fun `test findUser`() = runBlocking {
        val user = service.findUser(testUserId, testGuildId)
        assertEquals(testUserId, user.userId)
        assertContains(user.getNicknames(), testGuildId)
    }

    @Test
    fun `test findRole`() = runBlocking {
        val role = service.findRole(testGuildId, testRoleId)
        assertEquals(testRoleId, role.roleId)
        assertEquals("@全体成员", role.name)
    }

    @Test
    fun `test findChannel`() = runBlocking {
        val channel = service.findChannel(testChannelId)
        assertEquals(testChannelId, channel.channelId)
        assertEquals(testGuildId, channel.guildId)
    }
}
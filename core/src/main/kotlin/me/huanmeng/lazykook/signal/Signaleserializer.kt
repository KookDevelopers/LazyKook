package me.huanmeng.lazykook.signal

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import me.huanmeng.lazykook.entity.ChannelType
import me.huanmeng.lazykook.signal.event.SignalData
import me.huanmeng.lazykook.signal.event.SignalEventType
import me.huanmeng.lazykook.signal.event.SignalEventType.SYSTEM
import me.huanmeng.lazykook.signal.event.SignalSystemEventType

/**
 * 2024/4/18<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class SignalEventDeserializer : JsonDeserializer<SignalData>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): SignalData {
        val tree = p.codec.readValue(p, ObjectNode::class.java)
        val channelType = ctxt.readTreeAsValue(tree["channel_type"], ChannelType::class.java)
        val type = ctxt.readTreeAsValue(tree["type"], SignalEventType::class.java)
        val targetId = tree["target_id"].asText()
        val authorId = tree["author_id"].asText()
        val content = ctxt.readTreeAsValue(tree["content"], Any::class.java)
        val nonce = tree["nonce"].asText()
        val msgId = tree["msg_id"].asText()
        val msgTimestamp = tree["msg_timestamp"].asLong()
        val extraNode = tree["extra"]
        val extra = if (type == SYSTEM) {
            val extraType = ctxt.readTreeAsValue(extraNode["type"], SignalSystemEventType::class.java)
            ctxt.readTreeAsValue(extraNode, extraType.clazz)
        } else {
            type.clazz?.let {
                ctxt.readTreeAsValue(extraNode, it)
            } ?: throw IllegalArgumentException("type: $type, extra: $extraNode")
        }
        return SignalData(channelType, type, targetId, authorId, content, msgId, msgTimestamp, nonce, extra)
    }
}

class SignalExtraDeserializer : JsonDeserializer<Any>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Any? {
        val node = p.codec.readValue(p, JsonNode::class.java)
        if (node.isNull) {
            return null
        }
        if (node.has("channel_type")) {
            return ctxt.readTreeAsValue(node, SignalData::class.java)
        }
        return ctxt.readTreeAsValue(node, Map::class.java)
    }
}
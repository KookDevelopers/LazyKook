package me.huanmeng.lazykook.message

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.ObjectNode

/**
 * 2024/4/17<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
class CardDeserializer : JsonDeserializer<Card>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Card {
        val tree = p.codec.readValue(p, ObjectNode::class.java)
        val models = tree.get("modules")
        val modules: MutableList<CardModule> = ArrayList()
        var type = "card"
        var theme: Theme = Theme.PRIMARY
        var color = "#aaaaaa"
        var size: ElementSize = ElementSize.LG
        models.elements().forEach {
            val module = when (val moduleType = it["type"].asText()) {
                "section" -> ctxt.readTreeAsValue(it, SectionModule::class.java)
                "header" -> ctxt.readTreeAsValue(it, HeaderModule::class.java)
                "image-group" -> ctxt.readTreeAsValue(it, ImageGroupModule::class.java)
                "container" -> ctxt.readTreeAsValue(it, ContainerModule::class.java)
                "action-group" -> ctxt.readTreeAsValue(it, ActionGroupModule::class.java)
                "context" -> ctxt.readTreeAsValue(it, ContextModule::class.java)
                "divider" -> ctxt.readTreeAsValue(it, DividerModule::class.java)
                "resource" -> ctxt.readTreeAsValue(it, ResourceModule::class.java)
                "countdown" -> ctxt.readTreeAsValue(it, CountdownModule::class.java)
                "invite" -> ctxt.readTreeAsValue(it, InviteModule::class.java)
                else -> {
                    throw IllegalArgumentException("未知模块类型: $moduleType")
                }
            }
            modules.add(module)
        }
        if (tree.has("theme")) {
            theme = ctxt.readTreeAsValue(tree.get("theme"), Theme::class.java)
        }
        if (tree.has("color")) {
            color = tree.get("color").asText()
        }
        if (tree.has("size")) {
            size = ctxt.readTreeAsValue(tree.get("size"), ElementSize::class.java)
        }
        if (tree.has("type")) {
            type = tree.get("type").asText()
        }
        return Card(modules, type, theme, color, size)
    }

}

class ContextElementDeserializer : JsonDeserializer<ContextElement>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ContextElement? {
        val tree = p.codec.readValue(p, ObjectNode::class.java)
        val type = tree["type"].asText()
        when (type) {
            "plain-text", "kmarkdown" -> {
                return text(tree, ctxt)
            }

            "image" -> {
                return image(tree, ctxt)
            }
        }
        return null
    }
}

class SectionElementDeserializer : JsonDeserializer<SectionElement>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): SectionElement? {
        val tree = p.codec.readValue(p, ObjectNode::class.java)
        val type = tree["type"].asText()
        when (type) {
            "plain-text", "kmarkdown" -> {
                return text(tree, ctxt)
            }

            "paragraph" -> {
                val cols = if (tree.has("cols")) tree["cols"].asInt() else 3
                val fields = arrayListOf<Text>()
                tree["fields"].elements().forEach {
                    fields.add(ctxt.readTreeAsValue(it, Text::class.java))
                }
                return Paragraph(cols, fields)
            }
        }
        return null
    }
}

class AccessoryDeserializer : JsonDeserializer<Accessory>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Accessory? {
        val tree = p.codec.readValue(p, ObjectNode::class.java)
        val type = tree["type"].asText()
        when (type) {
            "image" -> {
                return image(tree, ctxt)
            }

            "button" -> {
                val text = ctxt.readTreeAsValue(tree["text"], Text::class.java)
                val value = if (tree.has("value")) tree["value"].asText() else ""
                val theme = if (tree.has("theme")) ctxt.readTreeAsValue(
                    tree["theme"],
                    Theme::class.java
                ) else Theme.NONE
                val click = if (tree.has("click")) ctxt.readTreeAsValue(
                    tree["click"],
                    ButtonClickType::class.java
                ) else ButtonClickType.NONE
                return Button(text, value, theme, click)
            }
        }
        return null
    }
}

private fun text(
    tree: ObjectNode,
    ctxt: DeserializationContext
): Text {
    val content = tree["content"].asText()
    val emoji = if (tree.has("emoji")) tree["emoji"].asBoolean() else true
    return Text(ctxt.readTreeAsValue(tree["type"], TextType::class.java), emoji, content)
}

private fun image(
    tree: ObjectNode,
    ctxt: DeserializationContext
): Image {
    val src = tree["src"].asText()
    val alt = if (tree.has("alt")) tree["alt"].asText() else ""
    val size = if (tree.has("size")) ctxt.readTreeAsValue(
        tree["size"],
        ElementSize::class.java
    ) else ElementSize.LG
    val circle = if (tree.has("circle")) tree["circle"].asBoolean() else false
    return Image(src, alt, size, circle)
}
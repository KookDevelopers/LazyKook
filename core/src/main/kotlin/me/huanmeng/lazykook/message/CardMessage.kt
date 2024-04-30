package me.huanmeng.lazykook.message

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */

@JsonSerialize(using = CardMessageSerializer::class)
data class CardMessage(
    val elements: List<Card>
)

// Card
@JsonDeserialize(using = CardDeserializer::class)
data class Card(
    val modules: List<CardModule>,
    val type: String = "card",
    val theme: Theme = Theme.PRIMARY,
    val color: String = "#aaaaaa",
    val size: ElementSize = ElementSize.LG,
)

// Modules
sealed class CardModule(
    open val type: String,
)

data class SectionModule(
    val text: SectionElement,
    val mode: SectionMode = SectionMode.LEFT,
    val accessory: Accessory? = null,
    override val type: String = "section",
) : CardModule("section") {}

data class HeaderModule(
    val text: Text,
    override val type: String = "header",
) : CardModule("header") {}

data class ImageGroupModule(
    val elements: List<Image>,
    override val type: String = "image-group",
) : CardModule("image-group") {}

data class ContainerModule(
    val elements: List<Image>,
    override val type: String = "container",
) : CardModule("container") {}

data class ActionGroupModule(
    val elements: List<Button>,
    override val type: String = "action-group",
) : CardModule("action-group") {}

data class ContextModule(
    val elements: List<ContextElement>, override val type: String = "context"
) : CardModule("context") {}

data class DividerModule(override val type: String = "divider") : CardModule("divider")

data class ResourceModule(
    override val type: String,
    val src: String,
    val title: String = "",
    val cover: String = "",
) : CardModule(type) {
    constructor(type: ResourceType, src: String, title: String = "", cover: String = "") : this(
        type.name.lowercase(), src, title, cover
    )

    constructor(type: ResourceType, src: String) : this(type.name.lowercase(), src, "", "")
}

data class CountdownModule(
    val startTime: Long,
    val endTime: Long,
    val mode: CountdownType = CountdownType.HOUR,
    override val type: String = "countdown"
) : CardModule("countdown")

data class InviteModule(
    val code: String,
    override val type: String = "invite",
) : CardModule("invite")

// Elements
sealed class Element {
    abstract val type: Any
}

@JsonDeserialize(using = ContextElementDeserializer::class)
interface ContextElement

@JsonDeserialize(using = SectionElementDeserializer::class)
interface SectionElement

@JsonDeserialize(using = AccessoryDeserializer::class)
interface Accessory

data class Text(
    override val type: TextType = TextType.PLAIN_TEXT,
    val emoji: Boolean = true,
    val content: String,
) : Element(), ContextElement, SectionElement

data class Paragraph(
    val cols: Int = 3,
    val fields: List<Text>,
    override val type: String = "paragraph",
) : Element(), SectionElement


data class Image(
    val src: String,
    val alt: String = "",
    val size: ElementSize = ElementSize.LG,
    val circle: Boolean = false,
    override val type: String = "image",
) : Element(), ContextElement, Accessory

data class Button(
    val text: Text,
    val value: String = "",
    val theme: Theme = Theme.NONE,
    val click: ButtonClickType = ButtonClickType.NONE,
    override val type: String = "button",
) : Element(), Accessory

enum class SectionMode(@JsonValue val value: String) {
    LEFT("left"), RIGHT("right"),
}

enum class TextType(@JsonValue val value: String) {
    PLAIN_TEXT("plain-text"), KMARKDOWN("kmarkdown"),
}

enum class ButtonClickType(@JsonValue val value: String) {
    @JsonEnumDefaultValue
    NONE(""), VAL("return-val"), LINK("link"),
}

// Elements - End

enum class Theme(@JsonValue val value: String) {
    @JsonEnumDefaultValue
    NONE(""),

    PRIMARY("primary"), SECONDARY("secondary"), INFO("info"), DANGER("danger"), WARNING("warning"), SUCCESS("success"),
}

enum class ElementSize(@JsonValue val value: String) {
    @JsonEnumDefaultValue
    LG("lg"), SM("sm")
}

enum class ResourceType(@JsonValue val value: String) {
    FILE("file"), VIDEO("video"), AUDIO("audio"),
}

enum class CountdownType(@JsonValue val value: String) {
    DAY("day"), HOUR("hour"), SECOND("second"),
}
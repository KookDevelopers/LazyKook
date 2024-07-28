package me.huanmeng.lazykook.message

/**
 * 2024/4/16<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class KMarkdown(
    val contents: List<KMarkdownContent>
)

data class KMarkdownContent(
    val type: KMarkdownType,
    val content: String,
    val attribute: String? = null,
    val child: List<KMarkdownContent> = emptyList()
) {
    companion object {
        fun parseMarkdown(input: String): List<KMarkdownContent> {
            val elements = mutableListOf<KMarkdownContent>()
            var currentIndex = 0

            while (currentIndex < input.length) {
                var found = false

                // 尝试匹配任何Markdown类型
                for (type in KMarkdownType.entries) {
                    if (type == KMarkdownType.RAW) continue
                    if (input.startsWith(type.prefix, currentIndex)) {
                        val endPrefixIndex = currentIndex + type.prefix.length
                        val endIndex = if (type.suffix.isNotEmpty() || type.attributeSuffix?.isNotEmpty() == true) {
                            val s = if(type.suffix.isEmpty() && type.attributeSuffix!=null) type.attributeSuffix else type.suffix
                            input.indexOf(s, endPrefixIndex) + s.length
                        } else {
                            endPrefixIndex
                        }

                        if (endIndex >= endPrefixIndex) {
                            val s = if(type.suffix.isEmpty() && type.attributeSuffix!=null) type.attributeSuffix else type.suffix
                            val content = input.substring(endPrefixIndex, endIndex - s.length)
                            var attribute: String? = null

                            // 如果类型具有属性前后缀，则尝试解析属性
                            if (type.attributePrefix != null && type.attributeSuffix != null && type!=KMarkdownType.CODE_BLOCK) {
                                val startAttributeIndex = content.lastIndexOf(type.attributePrefix)
                                if (startAttributeIndex != -1) {
                                    val endAttributeIndex = content.indexOf(
                                        type.attributeSuffix,
                                        startAttributeIndex + type.attributePrefix.length
                                    )
                                    if (endAttributeIndex != -1) {
                                        attribute = content.substring(
                                            startAttributeIndex + type.attributePrefix.length,
                                            endAttributeIndex
                                        )
                                        elements.add(
                                            create(
                                                type,
                                                content.substring(0, startAttributeIndex),
                                                attribute
                                            )
                                        )
                                    }
                                } else {
                                    elements.add(create(type, content))
                                }
                            } else {
                                elements.add(create(type, content, null))
                            }
                            currentIndex = endIndex
                            found = true
                            break
                        }
                    }
                }

                if (!found) {
                    val nextSpecialIndex = (KMarkdownType.values().filter { it != KMarkdownType.RAW }.mapNotNull {
                        if (input.indexOf(it.prefix, currentIndex) >= 0) input.indexOf(
                            it.prefix,
                            currentIndex
                        ) else null
                    }.minOrNull() ?: input.length)

                    if (nextSpecialIndex > currentIndex) {
                        elements.add(
                            create(
                                KMarkdownType.RAW,
                                input.substring(currentIndex, nextSpecialIndex)
                            )
                        )
                        currentIndex = nextSpecialIndex
                    } else {
                        currentIndex++
                    }
                }
            }

            return elements
        }

        private fun create(type: KMarkdownType, content: String, attribute: String? = null): KMarkdownContent {
            var child: List<KMarkdownContent> = emptyList()
            if (type.hasChild) {
                child = parseMarkdown(content).filter { it.type != KMarkdownType.RAW }
            }
            return KMarkdownContent(type, content, attribute, child)
        }

    }
}

enum class KMarkdownType(
    val prefix: String,
    val suffix: String = prefix,
    val attributePrefix: String? = null,
    val attributeSuffix: String? = null,
    val type: KMarkdownCodeType = KMarkdownCodeType.APPEND_CONTENT,
    val hasChild: Boolean = true,
) {
    BOLD_ITALIC("***"), // 加粗斜体
    BOLD("**"), // 加粗
    ITALIC("*"), // 斜体
    STRIKE("~~"), // 删除线
    LINK("[", "]", "(", ")", hasChild = false), // 链接
    DIVIDER("---", "", type = KMarkdownCodeType.NO_CONTENT, hasChild = false), // 分割线
    QUOTE("> ", "\n\n"), // 引用
    UNDERLINE("(ins)"), // 下划线
    SPOILER("(spl)"), // 剧透
    EMOJI(":", hasChild = false), // emoji
    SERVER_EMOJI("(emj)", attributePrefix = "[", attributeSuffix = "]", hasChild = false), // 服务器emoji
    CHANNEL("(chn)", hasChild = false), // 频道
    MET("(met)", hasChild = false), // 提及
    ROLE("(rol)", hasChild = false),
    CODE_BLOCK("```", "", attributePrefix = "", attributeSuffix = "\n```", hasChild = false),
    LINE_CODE("`", hasChild = false),
    UNESCAPE("\\", "", hasChild = false),
    RAW("", "", "", "", KMarkdownCodeType.CONTENT, hasChild = false),
    ;

    fun toRaw(content: String, attribute: String? = null): String {
        return when (type) {
            KMarkdownCodeType.NO_CONTENT -> {
                prefix + suffix
            }

            KMarkdownCodeType.CONTENT -> {
                content
            }

            KMarkdownCodeType.APPEND_CONTENT -> "$prefix$content$suffix".let {
                if (attribute != null && attributePrefix != null && attributeSuffix != null) {
                    "$it$attributePrefix$attribute$attributeSuffix"
                } else it
            }
        }
    }
}

enum class KMarkdownCodeType {
    APPEND_CONTENT, NO_CONTENT, CONTENT,
}
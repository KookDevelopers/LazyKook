package me.huanmeng.lazykook.codegen.model.method

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 2024/6/29<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
data class ConfiguredMethodData(
    val update: List<ConfiguredUpdateMethod>,
    @field:JsonAnySetter
    @get:JsonAnyGetter
    val methods: Map<String, String> = hashMapOf()
) {
    fun toList(): MutableList<MethodData> {
        val list: MutableList<MethodData> = arrayListOf()
        list.addAll(methods.map { MethodData(it.key, it.value) })
        update.forEach {
            list.add(
                MethodData(
                    "update(t: ${it.fromType})",
                    when (it.type) {
                        UpdateType.COPY_FROM -> "copyFrom(t)"
                    }
                )
            )
        }
        return list;
    }
}

data class ConfiguredUpdateMethod(
    @JsonProperty("class")
    val fromType: String,
    val type: UpdateType,
)


enum class UpdateType {
    COPY_FROM
}
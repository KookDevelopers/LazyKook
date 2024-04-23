package me.huanmeng.lazykook.annotation

/**
 * 2024/4/22<br>
 * LazyKook<br>
 * @author huanmeng_qwq
 */
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.TYPE_PARAMETER,
)
annotation class ByName(val value: String)

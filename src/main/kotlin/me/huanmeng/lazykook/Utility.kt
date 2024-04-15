package me.huanmeng.lazykook

fun <K, V> Map<K, V>.toPairs(): Array<Pair<K, V>> {
    return this.entries.map { it.toPair() }.toTypedArray()
}
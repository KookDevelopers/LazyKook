### LazyKook

<div style="text-align: center;">

![Version](https://img.shields.io/github/v/release/huanmeng-qwq/LazyKook?style=plastic)
![Code-Size](https://img.shields.io/github/languages/code-size/huanmeng-qwq/LazyKook?style=plastic)
![Repo-Size](https://img.shields.io/github/repo-size/huanmeng-qwq/LazyKook?style=plastic)
![License](https://img.shields.io/github/license/huanmeng-qwq/LazyKook?style=plastic)
![Language](https://img.shields.io/github/languages/top/huanmeng-qwq/LazyKook?style=plastic)
![Last-Commit](https://img.shields.io/github/last-commit/huanmeng-qwq/LazyKook?style=plastic)
</div>

该项目是一个以[KOOK开发者平台](https://developer.kookapp.cn)为基础提供的SDK实现
包含了对各种API接口的实现，同时也提供对应的组件内容的解析与包装、事件监听处理、websocket、webhook以及扩展等。

---

### 快速开始
前往[WIKI](https://github.com/huanmeng-qwq/LazyKook/wiki)查看。

### API支持
可以前往[[#20](https://github.com/huanmeng-qwq/LazyKook/issues/20)]查看目前支持的进度
如果你希望尽快的对某些API得到支持，请[及时提出](https://github.com/huanmeng-qwq/LazyKook/issues/new?assignees=&labels=enhancement&projects=&template=issue-feat.md&title=feat%3A+API%E9%9C%80%E6%B1%82)!

### 讨论
加入[频道](https://kook.top/IO8ZBE)一起讨论更多支持

> [!NOTE]
> 当前版本不包含对各类组件的快速操作，仅提供了最底层的事件监听、API请求、事件通讯、消息解析、语音播放流，获取User/Guild/Channel/Role。
> 1.0版本将提供完整支持。


### 成分
- [Kotlin](https://kotlinlang.org): 一个由Jetbrains公司开发的编程语言，对Java有着良好的兼容性，包含对Java友好的API
- [Ktor](https://ktor.io) 是基于**Kotlin**和 **Kotlin Coroutines**从头开始构建的。可以使用简洁的多平台语言，以及具有直观命令式流程的异步编程的强大功能。
- [Jackson](https://github.com/FasterXML/jackson-core): 一个快速且合规的流式 **JSON** 序列化/反序列化，具有类似 **StAX** 的 API，并支持通过注解快速解析/序列化 POJO
- [logback](https://github.com/qos-ch/logback): 一个可靠、通用、快速的Java日志框架


### 构建
最终产物在`./core/build/libs`文件夹中
```shell
gradlew target
```
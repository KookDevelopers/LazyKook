### LazyKook

开发中

> [!NOTE]
> 仅支持Java17及以上版本
> 
> 当前项目可以使用，但是还存在许多未发现的问题，如果遇到请及时报告Issue

### 使用 Using

```groovy
implementation("com.huanmeng-qwq.lazykook:lazykook:0.1.0")
```

```xml
<dependency>
    <groupId>com.huanmeng-qwq.lazykook</groupId>
    <artifactId>lazykook</artifactId>
    <version>0.1.0</version>
</dependency>
```

### 讨论 Discussions
加入[频道](https://kook.top/IO8ZBE)一起讨论更多支持

### 测试 Test
```shell
cd core
mkdir run
echo "token: xxx" > run/config.yml
gradlew test
```

### 构建 Building
最终产物在`./core/build/libs`文件夹中
```shell
gradlew target
```
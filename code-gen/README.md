> Q: 这是什么？
>
> A: `是为了解决字段不断变更的解决方案`
>
> Q: 原理是什么？
>
> A: `通过配置的文件生成相关的代码到对应的src目录`
>
>
>
字段结构

| 字段           | 描述                 | 类型           | 必选    |
|--------------|--------------------|--------------|-------|
| type         | 实际代码生成的类型          | string       | true  |
| field        | 对应json数据的字段名       | string       | false |
| supportTypes | 对应json数据的字段可能是那些类型 | array:string | false |

class结构

| 字段     | 描述    | 类型      |
|--------|-------|---------|
| isEnum | 是否是枚举 | boolean |
| fields | 类字段   | string  | 
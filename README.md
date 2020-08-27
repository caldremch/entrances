
[![](https://jitpack.io/v/caldremch/entrances.svg)](https://jitpack.io/#caldremch/entrances)

# Entry-List
Quickly create entrances for debugging various functions


# 问题记录

1.auto-service对kotlin项目不生效, 需要手动配置

```java

在src/main/
创建 resources/META-INF/services/javax.annotation.processing.Processor
同时在javax.annotation.processing.Processor文件中加上
包名.注解器名字

```

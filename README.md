
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

2.注解器不生效, 不执行

解决方法: 1.检查是否使用了kapt/annotationProcessor, 没有则加上  2.是否配置了支持注解类型检查getSupportedAnnotationTypes 3.检查是否配置了注解器信息(autoService 或者自己 手动配置)


3.kapt 报错

```java

* What went wrong:
Execution failed for task ':app:kaptDebugKotlin'.
> A failure occurred while executing org.jetbrains.kotlin.gradle.internal.KaptExecution
   > java.lang.reflect.InvocationTargetException (no error message)

```

一般是注解器 抛出了异常, 检查注解器代码
* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

点击Run with --stacktrace  获取更多信息, 便能看到详细的异常信息:
Caused by: java.lang.RuntimeException:
	at com.caldremch.android.entry.compiler.EntryProcessor.process(EntryProcessor.java:42)
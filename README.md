![main](https://github.com/caldremch/entrances/actions/workflows/android.yml/badge.svg?branch=master)



| entry                                                                                        | entry-annotation                                                                                        | entry-compiler                                                                                        |
|----------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
| ![maven](https://img.shields.io/maven-central/v/io.github.caldremch/entry?style=flat-square) | ![maven](https://img.shields.io/maven-central/v/io.github.caldremch/entry-annotation?style=flat-square) | ![maven](https://img.shields.io/maven-central/v/io.github.caldremch/entry-compiler?style=flat-square) |




# Entrance

Quickly create entrances for debugging various functions





###如何使用

1.在 build.gradle 文件中 添加依赖

#####kotlin

```groovy
apply plugin: 'com.google.devtools.ksp'
.....
.....
dependencies {
	implementation 'io.github.caldremch:entry:1.6.3'
	ksp 'io.github.caldremch:entry-compiler:1.6.3'
}
```

##### java 使用旧版compiler 1.6.2

```groovy
dependencies {
	implementation 'io.github.caldremch:entry:1.6.3'
	annotationProcessor 'io.github.caldremch:entry-compiler:1.6.2'
}  
```



2.创建入口

```java
@Entry
class AEntry : IEntry{
    
    override fun getTitle(): String {
        return "AEntry"
    }

    override fun onClick(context: Context) {
        Toast.makeText(context, "it a entry", Toast.LENGTH_SHORT).show()
    }
}
```



3.配置入口Activity

```xml
<activity android:name="com.caldremch.android.entry.EntryActivity">      
     <intent-filter>                                                      
         <action android:name="android.intent.action.MAIN" />             
                                                                          
         <category android:name="android.intent.category.LAUNCHER" />     
     </intent-filter>                                                     
</activity>                                                              
```



Ok.



![](img/entrance.png)![](img/entrance.png)



# 问题记录

1.auto-service对kotlin项目不生效, 需要手动配置

```java

在src/main/
创建 resources/META-INF/services/javax.annotation.processing.Processor
同时在javax.annotation.processing.Processor文件中加上
包名.注解器名字

```

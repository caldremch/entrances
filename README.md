

[![](https://jitpack.io/v/caldremch/entrances.svg)](https://jitpack.io/#caldremch/entrances)

|                            entry                             |                       entry-annotation                       | entry-compiler |
| --- | --- | --- |
| [ ![Download](https://api.bintray.com/packages/caldremch/maven/entry/images/download.svg) ](https://bintray.com/caldremch/maven/entry/_latestVersion) | [ ![Download](https://api.bintray.com/packages/caldremch/maven/entry-annotation/images/download.svg?version=1.3.0) ](https://bintray.com/caldremch/maven/entry-annotation/1.3.0/link) |[ ![Download](https://api.bintray.com/packages/caldremch/maven/entry-compiler/images/download.svg) ](https://bintray.com/caldremch/maven/entry-compiler/_latestVersion)|




# Entrance

Quickly create entrances for debugging various functions





###如何使用

1.在 build.gradle 文件中 添加依赖

#####kotlin

```groovy
apply plugin: 'kotlin-kapt'
.....
.....
dependencies {
	implementation 'com.caldremch.android:entry:1.5.0'
	kapt 'com.caldremch.android:entry-compiler:1.5.1'
}
```

##### java

```groovy
dependencies {
	implementation 'com.caldremch.android:entry:1.5.0'
	annotationProcessor 'com.caldremch.android:entry-compiler:1.5.1'
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



![image](http://github.com/caldremch/entrances/raw/master/img/entrance.png)



# 问题记录

1.auto-service对kotlin项目不生效, 需要手动配置

```java

在src/main/
创建 resources/META-INF/services/javax.annotation.processing.Processor
同时在javax.annotation.processing.Processor文件中加上
包名.注解器名字

```

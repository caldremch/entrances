//这个entry 模块有问题, 重新配置需要
plugins {
    id("com.android.library")
}

ext {
    this["myLibraryVersion"] = "1.2.1"
    this["myBintrayName"] = "entry"
    this["myArtifactId"] = "entry"
    this["myLibraryName"] = "entry for list"
    this["myLibraryDescription"] = "auto config entrys"
}


android {
    compileSdkVersion(30)
    defaultConfig {
        minSdkVersion(18)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.appcompat:appcompat:1.2.0")
    api("com.caldremch.android:entry-annotation:1.3.0")
}

apply(from="https://raw.githubusercontent.com/caldremch/gradle-maven-kotlin-dsl/master/bintray-with-maven-publish.gradle.kts")
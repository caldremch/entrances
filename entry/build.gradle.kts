//这个entry 模块有问题, 重新配置需要
plugins {
    id("com.android.library")
}

ext {
    this["myLibraryVersion"] = "1.3.3"
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

//tasks {
//    register("sourcesJar", Jar::class) {
//        archiveClassifier.set("sources")
//        from(android.sourceSets.getByName("main").java.srcDirs)
//    }
//    val javadoc =register("javadoc", Javadoc::class) {
////        options.addStringOption('Xdoclint:none', '-quiet')
////        options.addStringOption('encoding', 'UTF-8')
////        options.addStringOption('charSet', 'UTF-8')
//        setSource(android.sourceSets.getByName("main").java.srcDirs)
//        classpath += project.files(android.bootClasspath.joinToString(File.pathSeparator))
//    }
//
//    register("androidJavaDocsJar", Jar::class) {
//        archiveClassifier.set("javadoc")
//        dependsOn(javadoc)
//        from(javadoc.get().destinationDir)
//    }
//
//}
//
//publishing {
//    publications {
//        register("Publication", MavenPublication::class) {
//            afterEvaluate {
//                groupId = "com.caldremch.android"
//                artifactId = "entry"
//                version = "1.2.9.2"
//                val androidJavaDocsJar by tasks
//                val sourcesJar by tasks
//                artifact(sourcesJar)
//                artifact(androidJavaDocsJar)
//                from(components["debug"])
//            }
//        }
//    }
//}
//
//
//apply(from="https://raw.githubusercontent.com/caldremch/gradle-maven-kotlin-dsl/master/bintray-with-maven-publish.gradle")

//apply(from="../bintray-with-maven-publish.gradle")

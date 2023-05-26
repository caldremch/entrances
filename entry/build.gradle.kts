plugins {
    id("com.android.library")
}

ext {
    this["myLibraryVersion"] = properties["entry_version"]
    this["libraryName"] = "entry"
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
    lintOptions {
        isAbortOnError =  false
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.appcompat:appcompat:1.3.1")
    api("io.github.caldremch:entry-annotation:${properties["entry_annotation_version"]}")
    api(
        "androidx.annotation:annotation:1.2.0"
    )
    implementation("androidx.recyclerview:recyclerview:1.2.1")
}

val publishPath = "${rootProject.projectDir.absolutePath}/gradle-maven-kotlin-dsl/mavencentral-with-maven-publish.gradle"
val publishFile = File(publishPath)
if(publishFile.exists()){
    apply(from=publishPath)
}

if(project.hasProperty("githubReleaseToken")){
    val path = "${rootProject.projectDir.absolutePath}/github-release.gradle"
    apply(from=path)
}


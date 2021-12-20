plugins {
    id("com.android.library")
}

ext {
    this["myLibraryVersion"] = "1.6.1"
    this["libraryVersion"] = "1.0.0"
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

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.appcompat:appcompat:1.3.1")
    api("io.github.caldremch:entry-annotation:${properties["entry_annotation_version"]}")
    api(
        "androidx.annotation:annotation:1.2.0"
    )
    implementation("androidx.recyclerview:recyclerview:1.1.0")
}

apply(from="../gradle-maven-kotlin-dsl/mavencentral-with-maven-publish.gradle")
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

ext {
    this["myLibraryVersion"] = "1.4.0"
    this["myBintrayName"] = "entry-compiler"
    this["myArtifactId"] = "entry-compiler"
    this["myLibraryName"] = "entry annotation compiler"
    this["myLibraryDescription"] = "compiler for entry annotaion"
}


dependencies {
     val auto_service = "com.google.auto.service:auto-service:1.0-rc6"
     val javapoet = "com.squareup:javapoet:1.10.0"
    kapt(auto_service)
    implementation(auto_service)
    implementation(javapoet)
    implementation("com.caldremch.android:entry-annotation:1.3.0")
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}


repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
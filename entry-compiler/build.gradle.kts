import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

ext {
    this["myLibraryVersion"] = "1.6.0"
    this["myBintrayName"] = "entry-compiler"
    this["myArtifactId"] = "entry-compiler"
    this["myLibraryName"] = "entry annotation compiler"
    this["myLibraryDescription"] = "compiler for entry annotaion"
}


dependencies {
     val javapoet = "com.squareup:javapoet:1.10.0"
    implementation(javapoet)
    implementation("com.caldremch.android:entry-annotation:1.6.0")
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

apply(from = "https://raw.githubusercontent.com/caldremch/gradle-maven-kotlin-dsl/master/bintray-with-maven-publish.gradle")

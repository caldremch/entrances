import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

ext {
    this["libraryName"] = "entry-compiler"
    this["myLibraryVersion"] = "1.6.3"
    this["myBintrayName"] = "entry-compiler"
    this["myArtifactId"] = "entry-compiler"
    this["myLibraryName"] = "entry annotation compiler"
    this["myLibraryDescription"] = "compiler for entry annotaion"
}


dependencies {
    implementation("io.github.caldremch:entry-annotation:${properties["entry_annotation_version"]}")
    implementation(kotlin("stdlib-jdk8"))
    // ksp deps https://github.com/google/ksp/releases/tag/1.7.20-1.0.6
    implementation("com.google.devtools.ksp:symbol-processing-api:1.6.10-1.0.4")
    // https://square.github.io/kotlinpoet/
    implementation("com.squareup:kotlinpoet:1.11.0")
    implementation("com.squareup:kotlinpoet-ksp:1.11.0")
    implementation("com.squareup:kotlinpoet-metadata:1.11.0")
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


if(rootProject.file("gradle-maven-kotlin-dsl").exists()){
    apply(from="../gradle-maven-kotlin-dsl/mavencentral-with-maven-publish.gradle")
}


if(project.hasProperty("githubReleaseToken")){
    val path = "${rootProject.projectDir.absolutePath}/github-release.gradle"
    println("path=$path")
    apply(from=path)
}

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

ext {
    this["myLibraryVersion"] = "1.3.1"
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
    compileOnly("com.caldremch.android:entry-annotation:1.3.0")
}

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0")
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

apply(from="https://raw.githubusercontent.com/caldremch/gradle-maven-kotlin-dsl/master/bintray-with-maven-publish.gradle")



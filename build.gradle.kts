buildscript {
  dependencies {
    classpath("com.android.tools.build:gradle:8.1.0")
    classpath("com.google.gms:google-services:4.4.1")
    classpath("com.google.firebase:firebase-appdistribution-gradle:4.2.0")
  }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  id("com.android.application") version "8.2.0" apply false
  id("org.jetbrains.kotlin.android") version "1.9.0" apply false
  id("com.android.library") version "8.2.0" apply false
  id("com.google.dagger.hilt.android") version "2.48.1" apply false
  id ("io.realm.kotlin") version "1.11.0" apply false
  id("com.google.gms.google-services") version "4.4.1" apply false
}
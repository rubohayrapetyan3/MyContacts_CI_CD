plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  kotlin("kapt")
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "com.test.mycontacts"
  compileSdk = 34

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  implementation(project(mapOf("path" to ":domain")))
  implementation(project(mapOf("path" to ":entity")))
  implementation(project(mapOf("path" to ":common")))

  implementation("com.google.dagger:hilt-android:2.48.1")
  kapt("com.google.dagger:hilt-android-compiler:2.48.1")

  implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
  implementation("androidx.activity:activity-compose:1.8.2")

  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.11.0")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

  testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
  testImplementation ("io.mockk:mockk:1.12.4")
  testImplementation ("app.cash.turbine:turbine:0.12.1")
  testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
  testImplementation ("com.android.support.test:runner:1.0.2")
}
kapt {
  correctErrorTypes =  true
}
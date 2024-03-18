plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
  id("com.google.dagger.hilt.android")
  id ("io.realm.kotlin")
}

android {
  namespace = "com.test.domain"
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
//  implementation(project(mapOf("path" to ":data")))
  implementation(project(mapOf("path" to ":entity")))
  implementation(project(mapOf("path" to ":common")))

  implementation("io.realm.kotlin:library-base:1.11.0")

  implementation("com.google.dagger:hilt-android:2.48.1")
  testImplementation("junit:junit:4.12")
  kapt("com.google.dagger:hilt-android-compiler:2.48.1")

  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.11.0")
  testImplementation("junit:junit:4.13.2")
  testImplementation ("io.mockk:mockk:1.12.4")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
kapt {
  correctErrorTypes =  true
}
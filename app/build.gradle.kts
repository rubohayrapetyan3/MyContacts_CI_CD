plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("kapt")
  id("com.google.dagger.hilt.android")
  id ("io.realm.kotlin")
  id("com.google.firebase.appdistribution")
  id("com.google.gms.google-services")
}

android {
  namespace = "com.test.mycontacts"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.test.mycontacts"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      firebaseAppDistribution {
        releaseNotesFile="/path/to/releasenotes.txt"
        testers="rubenhayrapetyan3@gmail.com, rubohayrapetyan3@gmail.com"
      }
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
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  implementation(project(mapOf("path" to ":data")))
  implementation(project(mapOf("path" to ":domain")))
  implementation(project(mapOf("path" to ":presentation")))
  implementation(project(mapOf("path" to ":common")))
  implementation(project(mapOf("path" to ":entity")))

  implementation(platform("com.google.firebase:firebase-bom:32.7.4"))

  implementation("io.realm.kotlin:library-base:1.11.0")

  implementation("io.coil-kt:coil-compose:2.6.0")

  testImplementation("junit:junit:4.12")
  implementation("com.google.dagger:hilt-android:2.48.1")
  kapt("com.google.dagger:hilt-android-compiler:2.48.1")

  // Kotlin
  implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

  implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
  implementation("androidx.navigation:navigation-compose:2.7.7")

  implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.7")
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
  implementation("androidx.activity:activity-compose:1.8.2")
  implementation(platform("androidx.compose:compose-bom:2023.08.00"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material3:material3")
  implementation("com.google.android.material:material:1.11.0")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-tooling")
  debugImplementation("androidx.compose.ui:ui-test-manifest")
}
kapt {
  correctErrorTypes =  true
}
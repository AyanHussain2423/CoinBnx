plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.coinbnx"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.coinbnx"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true

    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.database)
    implementation(libs.androidx.foundation.android)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.material.icons.extended)
    implementation(libs.haze)

    // Hilt - Dependency Injection
    implementation ("com.google.dagger:hilt-android:2.52")
    kapt ("com.google.dagger:hilt-compiler:2.52")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1") // Check for the latest version

    // Retrofit - Networking
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")  // Check for the latest version
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")  // If you are using Gson for JSON parsing
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0") // If using RxJava, but optional for coroutines

    // ViewModel & LiveData
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")  // Latest version
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0")

    //navigattion
    implementation ("androidx.navigation:navigation-compose:2.6.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation ("io.coil-kt:coil-compose:2.1.0") // Coil for Compose
    implementation ("io.coil-kt:coil-svg:2.1.0") // Coil extension to load SVG

    implementation ("com.airbnb.android:lottie-compose:6.0.0") // Latest version at the time of writing

    // For Lottie animation rendering
    implementation ("com.airbnb.android:lottie:6.0.0") // Make sure this version matches
}

kapt{
    correctErrorTypes= true
}

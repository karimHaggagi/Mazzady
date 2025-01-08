plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.navigation.safeargs)
    alias(libs.plugins.dagger.hilt.android)
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.mazadytask"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mazadytask"
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
    dataBinding {
        enable = true
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)


    // retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    // okhttp3
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)

    // coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    // navigation
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)

    // lifecycle
    implementation (libs.androidx.lifecycle.runtime.ktx)

    //hilt
    implementation (libs.hilt.android)
    ksp(libs.hilt.compiler)


    implementation (libs.androidx.paging.runtime.ktx)
    implementation (libs.glide)

    // card slider

    // card slider
    implementation (libs.cardslider)
    // dots indicator
    implementation (libs.dotsindicator)


    // testing
    testImplementation (libs.hamcrest.all)
    testImplementation (libs.androidx.core.testing)
    testImplementation (libs.kotlinx.coroutines.android.v171)
    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation (libs.truth)
    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.inline)

    testImplementation (libs.turbine)
    testImplementation (libs.core.ktx)
    testImplementation (libs.androidx.junit.ktx)
    testImplementation (libs.androidx.rules)
    testImplementation(libs.junit)

    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.kotlin)


    // AndroidX Test - Instrumented testing
    androidTestImplementation (libs.androidx.core.ktx.v150)
    androidTestImplementation (libs.androidx.junit.ktx.v115)
    androidTestImplementation (libs.kotlinx.coroutines.test.v152)
    androidTestImplementation (libs.androidx.rules.v150)
    androidTestImplementation (libs.androidx.room.testing)
    androidTestImplementation (libs.androidx.core.testing)
    androidTestImplementation (libs.androidx.espresso.contrib)
    androidTestImplementation (libs.androidx.espresso.intents)
    androidTestImplementation (libs.androidx.idling.concurrent)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
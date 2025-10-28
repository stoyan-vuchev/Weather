plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt.android)
}

android {

    namespace = "com.stoyanvuchev.weather"

    compileSdk {
        version = release(36)
    }

    defaultConfig {

        applicationId = "com.stoyanvuchev.weather"

        minSdk = 33
        targetSdk = 36

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

}

dependencies {

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.google.material)

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // DI
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.viewmodel)
    ksp(libs.androidx.hilt.compiler)

    // Local Storage
    implementation(libs.datastore.preferences)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Networking
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)

    // Serialization
    implementation(libs.kotlinx.json.serialization)

    // Fancy UI
    implementation(libs.stoyanVuchev.systemUIBarsTweaker)
    implementation(libs.stoyanVuchev.squircleShape)
    implementation(libs.chrisBanes.haze)

    // Android Testing
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.assertK)
    androidTestImplementation(libs.cashApp.turbine)

    // Unit Testing
    testImplementation(libs.junit)
    testImplementation(libs.assertK)
    testImplementation(libs.cashApp.turbine)

    // Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}
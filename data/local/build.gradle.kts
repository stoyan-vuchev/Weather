plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {

    namespace = "com.stoyanvuchev.weather.data.local"

    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

}

dependencies {

    implementation(project(":domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.json.serialization)
    implementation(libs.datastore.preferences)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.assertK)
    testImplementation(libs.cashApp.turbine)
    testImplementation(libs.coroutines.test)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.assertK)
    androidTestImplementation(libs.cashApp.turbine)
    androidTestImplementation(libs.coroutines.test)

}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}
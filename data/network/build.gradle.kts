import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.android)
}

android {

    namespace = "com.stoyanvuchev.weather.data.network"
    compileSdk {
        version = release(36)
    }

    defaultConfig {

        minSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField(
            "String",
            "API_KEY",
            gradleLocalProperties(
                rootDir,
                providers
            ).getProperty("api.key")
        )

        buildConfigField(
            "String",
            "HOST_URL",
            gradleLocalProperties(
                rootDir,
                providers
            ).getProperty("host.url")
        )

        buildConfigField(
            "String",
            "HOME_GET_REQUEST",
            gradleLocalProperties(
                rootDir,
                providers
            ).getProperty("home.get.request")
        )

        buildConfigField(
            "String",
            "SEARCH_GET_REQUEST",
            gradleLocalProperties(
                rootDir,
                providers
            ).getProperty("search.get.request")
        )

        buildConfigField(
            "String",
            "REVERSE_SEARCH_GET_REQUEST",
            gradleLocalProperties(
                rootDir,
                providers
            ).getProperty("reverse.search.get.request")
        )

        buildConfigField(
            "String",
            "EXCLUDE_PARAMETERS",
            gradleLocalProperties(
                rootDir,
                providers
            ).getProperty("exclude.parameters")
        )

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

    buildFeatures {
        buildConfig = true
    }

}

dependencies {

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.kotlinx.json.serialization)

    testImplementation(libs.junit)
    testImplementation(libs.assertK)
    testImplementation(libs.cashApp.turbine)
    testImplementation(libs.coroutines.test)

}
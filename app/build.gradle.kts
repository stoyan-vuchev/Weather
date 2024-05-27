import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.daggerHilt.android.gradle)
}

android {

    namespace = "com.stoyanvuchev.weather"
    compileSdk = 34

    signingConfigs {

        getByName("debug") {
            storeFile = getFile("keystore.path")
            storePassword = getProperty("keystore.password")
            keyAlias = getProperty("key.alias")
            keyPassword = getProperty("key.password")
        }

        create("release") {
            storeFile = getFile("keystore.path")
            storePassword = getProperty("keystore.password")
            keyAlias = getProperty("key.alias")
            keyPassword = getProperty("key.password")
        }

    }

    defaultConfig {

        applicationId = "com.stoyanvuchev.weather"
        minSdk = 27
        targetSdk = 34

        versionCode = 1
        versionName = "0.0.24.5.5.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        includeBuildConfigFields()

    }

    buildTypes {

        debug {

            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }

        release {

            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            signingConfig = signingConfigs.getByName("release")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

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
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = extra["compose.compiler.version"] as String
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    // Core Dependencies

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.work)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.materialDesign)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Compose Dependencies

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.runtime)
    implementation(libs.compose.foundation)
    implementation(libs.compose.animation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.material3.windowSizeClass)

    debugImplementation(platform(libs.compose.bom))
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)

    // Look & Feel Dependencies

    implementation(libs.systemUiBarsTweaker)
    implementation(libs.squircleShape)
    implementation(libs.responsiveScaffold)

    // Local Storage Dependencies

    implementation(libs.datastore.preferences)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Dependency Injection Dependencies

    implementation(libs.daggerHilt.android)
    ksp(libs.daggerHilt.compiler)

    androidTestImplementation(libs.daggerHilt.android.testing)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.hilt.work)
    ksp(libs.androidx.hilt.compiler)

    // Networking Dependencies

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.loggingInterceptor)

    // Serialization Dependencies

    implementation(libs.gson)

    // Widget Dependencies

    implementation(libs.glance)
    implementation(libs.glance.appwidget)
    implementation(libs.glance.tools.appwidget.host)
    implementation(libs.glance.tools.appwidget.configuration)

    // Testing Dependencies

    testImplementation(libs.assertK)
    testImplementation(libs.appCash.turbine)
    testImplementation(libs.coroutinesTest)

    androidTestImplementation(libs.assertK)
    androidTestImplementation(libs.appCash.turbine)
    androidTestImplementation(libs.coroutinesTest)

}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

private fun ApplicationDefaultConfig.includeBuildConfigFields() {

    buildConfigField(
        "String",
        "BASE_URL",
        getProperty("base.url")
    )

    buildConfigField(
        "String",
        "FORECAST_REQUEST",
        getProperty("forecast.request")
    )

    buildConfigField(
        "String",
        "CURRENT_INFO_BASIC",
        getProperty("current.info.basic")
    )

    buildConfigField(
        "String",
        "CURRENT_INFO",
        getProperty("current.info")
    )

    buildConfigField(
        "String",
        "HOURLY_INFO",
        getProperty("hourly.info")
    )

    buildConfigField(
        "String",
        "DAILY_INFO",
        getProperty("daily.info")
    )

}

private fun getFile(path: String): File = file(getProperty(path))
private fun getProperty(name: String): String =
    gradleLocalProperties(rootDir, providers).getProperty(name)
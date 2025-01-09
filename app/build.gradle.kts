plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.ksp)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.joe.moviedb"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.joe.moviedb"
        minSdk = 29
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
        viewBinding = true
    }
}

dependencies {
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material3)
    implementation(libs.coil)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.navigation)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    implementation(project(":core:domain"))
    implementation(project(":core:presentation"))
    implementation(project(":feature:details:movies"))
    implementation(project(":feature:details:tv"))
    implementation(project(":feature:popular:movies"))
    implementation(project(":feature:popular:tv"))

    testImplementation(libs.junit)
}
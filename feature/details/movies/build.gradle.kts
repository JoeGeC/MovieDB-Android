plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.ksp)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.joe.movieDetails"
    compileSdk = 35

    defaultConfig {
        minSdk = 29

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material3)
    implementation(libs.coil)
    implementation(libs.coil.okhttp)
    implementation(libs.shimmer)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.navigation)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:presentation"))
    implementation(project(":feature:details:base"))
    implementation(project(":feature:cast:movies"))

    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.retrofit.mock)
    testImplementation(libs.kotlinx.coroutines.test)
}
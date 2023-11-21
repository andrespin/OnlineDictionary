plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "andrespin.onlinedictionary"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "andrespin.onlinedictionary"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.versions.ktx)
    implementation(libs.versions.appcompat)
    implementation(libs.versions.material)
    implementation(libs.versions.constraintlayout)
    testImplementation(libs.versions.junit)
    androidTestImplementation(libs.versions.androidxjunit)
    androidTestImplementation(libs.versions.espressocore)

    // Hilt
    implementation(libs.hilt.android.core)
    kapt(libs.hilt.compiler)

    // Navigation
    implementation(libs.navigationfragmentktx)
    implementation(libs.navigationuiktx)
    implementation(libs.navigationdynamicfeaturesfragment)
    androidTestImplementation(libs.navigationtesting)

    // Modules
    implementation(project(":presentation"))
    implementation(project(":presentation:about_app"))
    implementation(project(":presentation:dictionary"))
    implementation(project(":presentation:settings"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":data:local"))
    implementation(project(":data:remote"))
}
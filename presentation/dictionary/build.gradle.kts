plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "andrespin.dictionary"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
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

    implementation (libs.lifecyclescope)
    implementation (libs.viewmodelscope)

    // Hilt
    implementation(libs.hilt.android.core)
    kapt(libs.hilt.compiler)

    implementation(project(":domain"))
    implementation(project(":presentation"))

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofitMoshi)
    implementation(libs.okHttp)
    implementation(libs.moshi)
    implementation(libs.moshiKotlin)
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Navigation
    implementation(libs.navigationfragmentktx)
    implementation(libs.navigationuiktx)
    implementation(libs.navigationdynamicfeaturesfragment)
    androidTestImplementation(libs.navigationtesting)

}
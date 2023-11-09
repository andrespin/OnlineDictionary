plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "andrespin.onlinedictionary"
    compileSdk = 34

    defaultConfig {
        applicationId = "andrespin.onlinedictionary"
        minSdk = 24
        targetSdk = 33
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
//    implementation (libs.versions.hiltandroid)
//    implementation (libs.versions.hiltcompiler)

    // Hilt
    implementation(libs.hilt.android.core)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)



//    implementation("com.google.android.material:material:1.10.0")
//    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


//    material = "com.google.android.material:material:1.10.0"
//    constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.4"
//    junit = "junit:junit:4.13.2"
//    androidx_junit = "androidx.test.ext:junit:1.1.5"
//    espresso-core = "androidx.test.espresso:espresso-core:3.5.1"

    // Hilt
//    implementation ("com.google.dagger:hilt-android:2.48.1")
//    implementation ("com.google.dagger:hilt-compiler:2.48.1")

    // Modules
//    implementation(project(":presentation"))
//    implementation(project(":presentation:about_app"))
//    implementation(project(":presentation:dictionary"))
//    implementation(project(":presentation:settings"))
//    implementation(project(":domain"))
//    implementation(project(":data"))
//    implementation(project(":data:local"))
//    implementation(project(":data:remote"))

}
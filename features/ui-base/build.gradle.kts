plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Build.compileSdk)
    defaultConfig {
        minSdkVersion(Build.minSdk)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
//    kapt.includeCompileClasspath = false
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

dependencies {

    api(project(":libraries:api"))
    api(project(":libraries:core-ui"))
    api(project(":libraries:core"))

    implementation(Libs.Hilt.library)
    implementation(Libs.AndroidX.Hilt.viewmodel)
    kapt(Libs.AndroidX.Hilt.compiler)
    kapt(Libs.Hilt.compiler)

    api(Libs.AndroidX.coreKtx)
    api(Libs.AndroidX.appcompat)
    api(Libs.lottie)
    api(Libs.Mdc.material)
}
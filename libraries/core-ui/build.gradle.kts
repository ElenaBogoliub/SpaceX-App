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
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

dependencies {
    api(project(":libraries:flow-mvi"))
    api(project(":libraries:core"))
    api(Libs.Coroutines.android)

    api(Libs.AndroidX.coreKtx)
    api(Libs.AndroidX.appcompat)
    api(Libs.AndroidX.Fragment.fragment)
    api(Libs.AndroidX.Fragment.fragmentKtx)
    api(Libs.AndroidX.Lifecycle.livedata)
    api(Libs.AndroidX.Lifecycle.viewmodel)
    kapt(Libs.AndroidX.Lifecycle.lifecycleCompiler)
    api(Libs.AndroidX.Navigation.ui)
    api(Libs.AndroidX.Navigation.fragment)

    implementation(Libs.Hilt.library)
    implementation(Libs.AndroidX.Hilt.viewmodel)
    kapt(Libs.Hilt.compiler)
    kapt(Libs.AndroidX.Hilt.compiler)
}
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
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
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

dependencies {
    api(Libs.Kotlin.stdlib)
    api(Libs.Coroutines.core)
    api(Libs.Network.Moshi.moshi)
    api(Libs.threeTenAbp)
    api(Libs.timber)

    implementation(Libs.Hilt.library)
    kapt(Libs.Hilt.compiler)

    debugImplementation(Libs.Debug.flipper)
    debugImplementation(Libs.Debug.flipperNetwork)
    releaseImplementation(Libs.Debug.flipperNoop)
    debugImplementation(Libs.Debug.soloader)
    debugImplementation(Libs.Debug.leakCanary)
}
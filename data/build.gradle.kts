plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(21)
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

    implementation(project(":libraries:core"))
    implementation(project(":libraries:api"))
    implementation(Libs.Kotlin.stdlib)

    implementation(Libs.AndroidX.Room.common)
    implementation(Libs.AndroidX.Room.runtime)
    implementation(Libs.AndroidX.Room.ktx)
    kapt(Libs.AndroidX.Room.compiler)

    implementation(Libs.threeTenBpNoTzdb)
    implementation(Libs.threeTenAbp)
    implementation(Libs.Hilt.library)
    kapt(Libs.Hilt.compiler)
}
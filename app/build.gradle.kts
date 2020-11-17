plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")

    kotlin("kapt")
    id("dagger.hilt.android.plugin")

    kotlin("android.extensions")
//    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Build.compileSdk)

    defaultConfig {
        applicationId(Build.applicationId)
        minSdkVersion(Build.minSdk)
        targetSdkVersion(Build.targetSdk)
        versionCode(Build.versionCode)
        versionName(Build.versionName)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            minifyEnabled(false)
            versionNameSuffix("-dev")
            applicationIdSuffix(".debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    lintOptions {
        isCheckReleaseBuilds = false
        isCheckDependencies = true
        isIgnoreTestSources = true
        isAbortOnError = false
        disable("StringFormatMatches")
        disable("Instantiatable")
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

kapt {
    correctErrorTypes = true
    useBuildCache = false
}

dependencies {
    implementation(project(":data"))
    implementation(project(":libraries:core"))
    implementation(project(":libraries:api"))
    implementation(project(":libraries:core-ui"))
    implementation(project(":features:ui-base"))
    implementation(project(":features:ui-next-launch"))
    implementation(project(":features:ui-launches"))
    implementation(project(":features:ui-rockets"))
    implementation(project(":features:ui-launch-details"))

    implementation(Libs.AndroidX.constraintlayout)
    implementation(Libs.Insetter.ktx)
    implementation(Libs.viewbindingdelegate)

    implementation(Libs.AndroidX.Hilt.viewmodel)
    implementation(Libs.Hilt.library)
    kapt(Libs.AndroidX.Hilt.compiler)
    kapt(Libs.Hilt.compiler)

    implementation(Libs.AndroidX.Room.runtime)
    implementation(Libs.AndroidX.Room.ktx)
    kapt(Libs.AndroidX.Room.compiler)

    implementation(Libs.Network.Retrofit.retrofit)
    implementation(Libs.Network.Retrofit.moshiConverter)
    implementation(Libs.Network.OkHttp.okhttp)
    implementation(Libs.Network.OkHttp.loggingInterceptor)
    implementation(Libs.Network.Moshi.moshi)
    implementation(Libs.Network.Retrofit.moshiConverter)
    implementation(Libs.Network.Moshi.adapters)
    implementation(Libs.timber)

    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}
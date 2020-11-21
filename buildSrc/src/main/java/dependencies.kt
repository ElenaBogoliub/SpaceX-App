
object Build {
    const val compileSdk = 30
    const val minSdk = 23
    const val targetSdk = 30

    const val applicationId = "com.ebogoliub.space"
    const val versionCode = 1
    const val versionName = "1.0.0"

    const val kotlinVersion = "1.4.0"
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.0"
}

object Libs {

    const val threeTenBp = "org.threeten:threetenbp:1.4.4"
    const val threeTenBpNoTzdb = "$threeTenBp:no-tzdb"
    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:1.2.4"

    const val timber = "com.jakewharton.timber:timber:4.7.1"

    const val viewbindingdelegate = "com.kirich1409.viewbindingpropertydelegate:viewbindingpropertydelegate:1.3.0"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.4"

    const val lottie = "com.airbnb.android:lottie:3.1.0"

    object Insetter {
        private const val version = "0.3.1"
        const val dbx = "dev.chrisbanes:insetter-dbx:$version"
        const val ktx = "dev.chrisbanes:insetter-ktx:$version"
    }

    object Mdc {
        const val material = "com.google.android.material:material:1.1.0"
    }

    object Kotlin {
        private const val version = Build.kotlinVersion
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.4.0"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Debug {
        private const val flipperVersion = "0.63.0"

        const val flipper = "com.facebook.flipper:flipper:$flipperVersion"
        const val flipperNetwork = "com.facebook.flipper:flipper-network-plugin:$flipperVersion"
        const val flipperNoop = "com.facebook.flipper:flipper-noop:$flipperVersion"
        const val soloader = "com.facebook.soloader:soloader:0.9.0"

        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.2"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.0-alpha01"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.2.0-alpha05"
        const val swiperefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0-rc01"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-rc1"
        const val coreKtx = "androidx.core:core-ktx:1.5.0-alpha02"

        object Navigation {
            private const val version = "2.3.0"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Fragment {
            private const val version = "1.3.0-alpha07"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Test {
            private const val version = "1.2.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2-rc01"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
            const val archCoreTesting = "androidx.arch.core:core-testing:2.1.0"
        }

        object Lifecycle {
            private const val version = "2.3.0-alpha06"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$version"
        }

        object Room {
            private const val version = "2.3.0-alpha03"
            const val common = "androidx.room:room-common:$version"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val testing = "androidx.room:room-testing:$version"
        }

        object Hilt {
            private const val version = "1.0.0-alpha02"
            const val work = "androidx.hilt:hilt-work:$version"
            const val viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:$version"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
        }
    }

    object Dagger {
        private const val version = "2.28.3"
        const val dagger = "com.google.dagger:dagger:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
    }

    object Hilt {
        private const val version = "2.29.1-alpha"
        const val library = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }

    object Network {
        object Retrofit {
            private const val version = "2.9.0"
            const val retrofit = "com.squareup.retrofit2:retrofit:$version"
            const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
        }

        object OkHttp {
            private const val version = "4.8.0"
            const val okhttp = "com.squareup.okhttp3:okhttp:$version"
            const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        }

        object Moshi {
            private const val version = "1.9.2"
            const val moshi = "com.squareup.moshi:moshi:$version"
            const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
            const val adapters = "com.squareup.moshi:moshi-adapters:$version"
        }
    }

    object Coil {
        private const val version = "1.0.0-rc1"
        const val coil = "io.coil-kt:coil:$version"
    }

    object Test {
        const val turbine = "app.cash.turbine:turbine:0.2.1"

        const val junit = "junit:junit:4.13"
        const val robolectric = "org.robolectric:robolectric:4.3.1"
        const val mockK = "io.mockk:mockk:1.10.0"

        val core = "androidx.test:core:1.1.0"
        val runner = "androidx.test:runner:1.1.1"
        val rules = "androidx.test:rules:1.1.1"

        val archCoreTesting = "androidx.arch.core:core-testing:2.0.0"
        val espressoCore = "androidx.test.espresso:espresso-core:3.1.1"
        val espressoContrib = "androidx.test.espresso:espresso-contrib:3.1.1"

        private const val kluentVersion = "1.61"
        val kluent = "org.amshove.kluent:kluent:$kluentVersion"
        val kluentAndroid = "org.amshove.kluent:kluent-android:$kluentVersion"
    }
}

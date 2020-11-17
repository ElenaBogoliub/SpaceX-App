import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath(Build.androidGradlePlugin)

        classpath(kotlin("gradle-plugin", version = Build.kotlinVersion))
        classpath(Libs.Kotlin.extensions)

        classpath(Libs.AndroidX.Navigation.safeArgs)
        classpath(Libs.Hilt.gradlePlugin)
    }
}

allprojects {
    repositories {
        jcenter()
        google()
        maven(url = "https://jitpack.io")
    }
}

subprojects {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            // Treat all Kotlin warnings as errors
            allWarningsAsErrors = false
            freeCompilerArgs += "-Xnew-inference"
            freeCompilerArgs += "-Xinline-classes"

            // Enable experimental coroutines APIs, including Flow
            freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
            freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.FlowPreview"
            freeCompilerArgs += "-Xopt-in=kotlin.Experimental"

            freeCompilerArgs += "-Xallow-jvm-ir-dependencies"

            freeCompilerArgs += "-Xjvm-default=all"

            // Set JVM target to 1.8
            jvmTarget = "1.8"
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
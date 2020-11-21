
plugins {
    `java-library`
    kotlin("jvm")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Coroutines.core)

    api(Libs.threeTenBpNoTzdb)
    implementation(Libs.threeTenAbp)

    implementation(Libs.Network.Retrofit.retrofit)
    kapt(Libs.Network.Moshi.codegen)
    implementation(Libs.Network.Moshi.moshi)
}
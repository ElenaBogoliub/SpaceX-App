package com.ebogoliub.spacex.inject

import com.ebogoliub.spacex.api.launches.LaunchesService
import com.ebogoliub.spacex.api.rocket.RocketsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ApiServiceModule {

    @Provides
    @Singleton
    fun launchesService(retrofit: Retrofit): LaunchesService = retrofit.createService()

    @Provides
    @Singleton
    fun rocketsService(retrofit: Retrofit): RocketsService = retrofit.createService()

    private inline fun <reified T> Retrofit.createService(): T {
        return create((T::class.java))
    }
}
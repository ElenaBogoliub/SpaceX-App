package com.ebogoliub.spacex

import android.app.Application
import com.ebogoliub.spacex.core.initializers.AppInitializers
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SpacexApplication : Application() {

    @Inject
    lateinit var initializers: AppInitializers

    override fun onCreate() {
        super.onCreate()

        initializers.init()
    }
}
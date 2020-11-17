package com.ebogoliub.spacex.core.crash

import com.ebogoliub.spacex.core.initializers.AppInitializer

class CrashlyticsInitializer(
    private val debug: Boolean
) : AppInitializer() {
    override fun initialize() {
//        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!debug)
    }
}
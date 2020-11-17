package com.ebogoliub.spacex.core.initializers

import android.app.Application
import com.ebogoliub.spacex.core.di.CoroutinesDispatchers
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.zone.ZoneRulesProvider
import javax.inject.Inject

class ThreeTenBpInitializer @Inject constructor(
    private val dispatchers: CoroutinesDispatchers,
    private val application: Application
) : AppInitializer() {

    override fun initialize() {
        AndroidThreeTen.init(application)

        GlobalScope.launch(dispatchers.io) {
            ZoneRulesProvider.getAvailableZoneIds()
        }
    }
}
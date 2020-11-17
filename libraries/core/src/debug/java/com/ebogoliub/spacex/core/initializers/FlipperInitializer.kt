package com.ebogoliub.spacex.core.initializers

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import javax.inject.Inject

class FlipperInitializer @Inject constructor(
    private val application: Application
) : AppInitializer() {

    override fun initialize() {
        SoLoader.init(application, false)
        if (FlipperUtils.shouldEnableFlipper(application)) {
            AndroidFlipperClient.getInstance(application).apply {
                addPlugin(InspectorFlipperPlugin(application, DescriptorMapping.withDefaults()))
                addPlugin(DatabasesFlipperPlugin(application))
                addPlugin(NetworkFlipperPlugin())
            }.start()
        }
    }
}
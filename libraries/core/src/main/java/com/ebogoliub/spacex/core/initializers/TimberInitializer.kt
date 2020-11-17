package com.ebogoliub.spacex.core.initializers

import timber.log.Timber
import javax.inject.Inject

class TimberInitializer @Inject constructor(
    private val trees: Set<@JvmSuppressWildcards Timber.Tree>
) : AppInitializer() {
    override fun initialize() {
        trees.forEach { Timber.plant(it) }
    }

    @Suppress("MagicNumber")
    override val priority: Int
        get() = 10
}

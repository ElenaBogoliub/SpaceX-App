package com.ebogoliub.spacex.core.initializers

abstract class AppInitializer {
    abstract fun initialize()

    open val priority: Int = 0
}
package com.ebogoliub.spacex.data

import com.ebogoliub.spacex.data.dao.*

interface SpacexDatabase {
    fun launchDao(): LaunchDao
    fun rocketsDao(): RocketsDao
}
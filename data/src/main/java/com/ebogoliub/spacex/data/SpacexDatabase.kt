package com.ebogoliub.spacex.data

import com.ebogoliub.spacex.data.daos.*

interface SpacexDatabase {
    fun launchDao(): LaunchDao
    fun rocketsDao(): RocketsDao
}
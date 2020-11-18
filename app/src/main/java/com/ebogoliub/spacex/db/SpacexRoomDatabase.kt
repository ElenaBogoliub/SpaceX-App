package com.ebogoliub.spacex.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ebogoliub.spacex.data.SpacexDatabase
import com.ebogoliub.spacex.data.entity.*
import com.ebogoliub.spacex.data.entity.converters.InstantConverter
import com.ebogoliub.spacex.data.entity.converters.SpacexTypeConverters
import com.ebogoliub.spacex.data.entity.converters.ListConverter
import com.ebogoliub.spacex.data.entity.converters.ZonedDateTimeConverter
import com.ebogoliub.spacex.data.entity.Launch

@Database(
    entities = [
        Launch::class,
        Rocket::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    ListConverter::class,
    InstantConverter::class,
    ZonedDateTimeConverter::class,
    SpacexTypeConverters::class
)

abstract class SpacexRoomDatabase : RoomDatabase(), SpacexDatabase
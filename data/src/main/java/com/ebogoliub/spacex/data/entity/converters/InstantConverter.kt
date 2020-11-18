package com.ebogoliub.spacex.data.entity.converters

import androidx.room.TypeConverter
import org.threeten.bp.Instant

class InstantConverter {
    @TypeConverter
    fun toInstant(value: Long?) = value?.let { Instant.ofEpochMilli(it) }

    @TypeConverter
    fun fromInstant(date: Instant?) = date?.toEpochMilli()
}

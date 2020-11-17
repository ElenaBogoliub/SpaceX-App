package com.ebogoliub.spacex.data.entities.converters

import androidx.room.TypeConverter
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class ZonedDateTimeConverter {
    @TypeConverter
    fun fromString(string: String?): ZonedDateTime? {
        return  string?.let { ZonedDateTime.parse(it, DateTimeFormatter.ISO_DATE_TIME) }
    }

    @TypeConverter
    fun toString(value: ZonedDateTime?): String? {
        return value?.format(DateTimeFormatter.ISO_DATE_TIME)
    }
}
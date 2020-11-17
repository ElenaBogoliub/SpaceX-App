package com.ebogoliub.spacex.data.entities.converters

import androidx.room.TypeConverter
import com.ebogoliub.spacex.data.entities.DatePrecision

class SpacexTypeConverters {

    @TypeConverter
    fun datePrecisionToString(datePrecision: DatePrecision): String? {
        return datePrecision.name
    }

    @TypeConverter
    fun stringToDatePrecision(s: String): DatePrecision? {
        return try {
            DatePrecision.valueOf(s)
        } catch (ex: IllegalArgumentException) {
            null
        }
    }
}
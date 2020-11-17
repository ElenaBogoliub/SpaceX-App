package com.ebogoliub.spacex.data.entities.converters

import androidx.room.TypeConverter

class ListConverter {

    @TypeConverter
    fun toString(list: List<String>): String {
        list.forEach { item ->
            require(DELIMITER !in item) { "$item must not contain delimiter $DELIMITER" }
        }
        return list.joinToString(DELIMITER)
    }

    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.takeUnless { it.isBlank() }?.split(DELIMITER) ?: emptyList()
    }

    @TypeConverter
    fun stringToListOfInt(value: String?): List<Int>? {
        return value.takeUnless { it.isNullOrBlank() }
            ?.split(DELIMITER)
            ?.map { id: String -> id.toInt() }
            ?: emptyList()
    }

    @TypeConverter
    fun listOfIntToString(value: List<Int>?): String? {
        return value?.joinToString(separator = DELIMITER)
    }

    companion object {
        private const val DELIMITER = ","
    }
}

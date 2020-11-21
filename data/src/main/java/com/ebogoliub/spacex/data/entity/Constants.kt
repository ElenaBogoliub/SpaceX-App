package com.ebogoliub.spacex.data.entity

enum class DatePrecision(val dateFormat: String) {
    YEAR("yyyy"),
    HALF("yyyy"),
    QUARTER("yyyy"),
    MONTH("MMM, yyyy"),
    DAY("d MMM, yyyy"),
    HOUR("hh:mm a, d MMM, yyyy"),
    UNKNOWN("'Unknown'")
}

fun String?.toDatePrecision(): DatePrecision {
    this ?: return DatePrecision.UNKNOWN
    return try {
        DatePrecision.valueOf(this)
    } catch (ex: IllegalArgumentException) {
        DatePrecision.UNKNOWN
    }
}
package com.ebogoliub.spacex.api.history

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.Instant
import org.threeten.bp.ZonedDateTime

@JsonClass(generateAdapter = true)
data class HistoricalEventDto(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "event_date_utc") val date: ZonedDateTime,
    @Json(name = "flight_number") val flightNumber: Int?,
    @Json(name = "details") val details: String,
    @Json(name = "links") val linksDto: LinksDto
)

@JsonClass(generateAdapter = true)
data class LinksDto(
    @Json(name = "reddit") val reddit: String?,
    @Json(name = "article") val article: String?,
    @Json(name = "wikipedia") val wikipedia: String?
)
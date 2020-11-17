package com.ebogoliub.spacex.api.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MissionSummaryDto(
    @Json(name = "name") val name: String,
    @Json(name = "flight") val flight: Int
)
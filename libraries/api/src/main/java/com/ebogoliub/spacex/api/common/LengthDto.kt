package com.ebogoliub.spacex.api.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LengthDto(
    @Json(name = "meters") val meters: Double?,
    @Json(name = "feet") val feet: Double?
)
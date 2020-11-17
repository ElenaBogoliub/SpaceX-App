package com.ebogoliub.spacex.api.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MassDto(
    @Json(name = "kg") val kg: Double,
    @Json(name = "lb") val lb: Double
)
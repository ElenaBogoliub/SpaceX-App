package com.ebogoliub.spacex.api.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThrustDto(
    @Json(name = "kN") val kN: Double,
    @Json(name = "lbf") val lbf: Double
)
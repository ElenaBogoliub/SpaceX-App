package com.ebogoliub.spacex.api.common

import com.ebogoliub.spacex.api.rocket.PayloadsDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SecondStageDto(
    @Json(name = "engines") val engines: Int,
    @Json(name = "fuel_amount_tons") val fuelAmountTons: Double?,
    @Json(name = "burn_time_sec") val burnTimeSecs: Double?,
    @Json(name = "thrust") val thrust: ThrustDto,
    @Json(name = "payloads") val payloads: PayloadsDto
)
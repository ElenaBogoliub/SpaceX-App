package com.ebogoliub.spacex.api.capsule

import com.ebogoliub.spacex.api.common.MissionSummaryDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class CapsuleDto(
    @Json(name = "capsule_serial") val serial: String,
    @Json(name = "capsule_id") val id: String,
    @Json(name = "status") val status: String,
    @Json(name = "original_launch") val originalLaunch: Date?,
    @Json(name = "missions") val missions: List<MissionSummaryDto>,
    @Json(name = "landings") val landings: Int,
    @Json(name = "type") val type: String,
    @Json(name = "details") val details: String?,
    @Json(name = "reuse_count") val reuseCount: Int
)
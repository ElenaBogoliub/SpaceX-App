package com.ebogoliub.spacex.api.launches

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.ZonedDateTime

@JsonClass(generateAdapter = true)
data class Payload(
    @Json(name = "payload_id") val id: String,
    @Json(name = "norad_id") val noradId: List<Int>,
    @Json(name = "reused") val reused: Boolean,
    @Json(name = "customers") val customers: List<String>,
    @Json(name = "nationality") val nationality: String?,
    @Json(name = "manufacturer") val manufacturer: String?,
    @Json(name = "payload_type") val payloadType: String,
    @Json(name = "payload_mass_kg") val payloadMassKg: Double?,
    @Json(name = "payload_mass_lbs") val payloadMassLbs: Double?,
    @Json(name = "orbit") val orbit: String?,
    @Json(name = "orbit_params") val orbitParams: OrbitParams
)

@JsonClass(generateAdapter = true)
data class OrbitParams(
    @Json(name = "reference_system") val referenceSystem: String?,
    @Json(name = "regime") val regime: String?,
    @Json(name = "longitude") val longitude: Double?,
    @Json(name = "semi_major_axis_km") val semiMajorAxisKm: Double?,
    @Json(name = "eccentricity") val eccentricity: Double?,
    @Json(name = "periapsis_km") val periapsisKm: Double?,
    @Json(name = "apoapsis_km") val apoapsisKm: Double?,
    @Json(name = "inclination_deg") val inclinationDeg: Double?,
    @Json(name = "period_min") val periodMin: Double?,
    @Json(name = "lifespan_years") val lifespanYears: Double?,
    @Json(name = "epoch") val epoch: ZonedDateTime?,
    @Json(name = "mean_motion") val meanMotion: Double?,
    @Json(name = "raan") val raan: Double?,
    @Json(name = "arg_of_pericenter") val argOfPericenter: Double?,
    @Json(name = "mean_anomaly") val meanAnomaly: Double?
)
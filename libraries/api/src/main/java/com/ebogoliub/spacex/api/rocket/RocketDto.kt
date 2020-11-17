package com.ebogoliub.spacex.api.rocket

import com.ebogoliub.spacex.api.common.LengthDto
import com.ebogoliub.spacex.api.common.MassDto
import com.ebogoliub.spacex.api.common.SecondStageDto
import com.ebogoliub.spacex.api.common.ThrustDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RocketDto(
    @Json(name = "id") val id: Int,
    @Json(name = "active") val active: Boolean,
    @Json(name = "stages") val stages: Int,
    @Json(name = "boosters") val boosters: Int,
    @Json(name = "cost_per_launch") val costPerLaunch: Long,
    @Json(name = "success_rate_pct") val successRate: Double,
    @Json(name = "first_flight") val firstFlight: String,
    @Json(name = "country") val country: String,
    @Json(name = "company") val company: String,
    @Json(name = "height") val height: LengthDto,
    @Json(name = "diameter") val diameter: LengthDto,
    @Json(name = "mass") val mass: MassDto,
    @Json(name = "payload_weights") val payloadWeights: List<PayloadWeight>,
    @Json(name = "first_stage") val firstStage: FirstStageDto,
    @Json(name = "second_stage") val secondStage: SecondStageDto,
    @Json(name = "engines") val engines: EnginesDto,
    @Json(name = "landing_legs") val landingLegs: LandingLegsDto,
    @Json(name = "wikipedia") val wikipedia: String,
    @Json(name = "description") val description: String,
    @Json(name = "rocket_id") val rocketId: String,
    @Json(name = "rocket_name") val rocketName: String,
    @Json(name = "rocket_type") val rocketType: String
)

@JsonClass(generateAdapter = true)
data class CompositeFairingDto(
    @Json(name = "height") val height: LengthDto,
    @Json(name = "diameter") val diameter: LengthDto
)

@JsonClass(generateAdapter = true)
data class EnginesDto(
    @Json(name = "number") val number: Int,
    @Json(name = "type") val type: String,
    @Json(name = "version") val version: String,
    @Json(name = "layout") val layout: String?,
    @Json(name = "engine_loss_max") val engineLossMax: Int?,
    @Json(name = "propellant_1") val propellant1: String,
    @Json(name = "propellant_2") val propellant2: String,
    @Json(name = "thrust_sea_level") val thrustSeaLevel: ThrustDto,
    @Json(name = "thrust_vacuum") val thrustVacuum: ThrustDto,
    @Json(name = "thrust_to_weight") val thrustToWeightRatio: Double?
)

@JsonClass(generateAdapter = true)
data class FirstStageDto(
    @Json(name = "reusable") val reusable: Boolean,
    @Json(name = "engines") val engines: Int,
    @Json(name = "fuel_amount_tons") val fuelAmountTons: Double,
    @Json(name = "burn_time_sec") val burnTimeSecs: Double?,
    @Json(name = "thrust_sea_level") val thrustSeaLevel: ThrustDto,
    @Json(name = "thrust_vacuum") val thrustVacuum: ThrustDto
)

@JsonClass(generateAdapter = true)
data class LandingLegsDto(
    @Json(name = "number") val number: Int,
    @Json(name = "material") val material: String?
)

@JsonClass(generateAdapter = true)
data class PayloadsDto(
    @Json(name = "option_1") val option1: String?,
    @Json(name = "option_2") val option2: String?,
    @Json(name = "composite_fairing") val compositeFairing: CompositeFairingDto
)

@JsonClass(generateAdapter = true)
data class PayloadWeight(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "kg") val weightKg: Double,
    @Json(name = "lb") val weightLb: Double
)
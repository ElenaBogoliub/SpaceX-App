package com.ebogoliub.spacex.api.dragon

import com.ebogoliub.spacex.api.common.LengthDto
import com.ebogoliub.spacex.api.common.MassDto
import com.ebogoliub.spacex.api.common.ThrustDto
import com.ebogoliub.spacex.api.common.VolumeDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DragonDto(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: String,
    @Json(name = "active") val active: Boolean,
    @Json(name = "crew_capacity") val crewCapacity: Int,
    @Json(name = "sidewall_angle_deg") val sideWallAngleDeg: Double,
    @Json(name = "orbit_duration_yr") val orbitDurationYear: Double,
    @Json(name = "dry_mass_kg") val dryMassKg: Double,
    @Json(name = "dry_mass_lb") val dryMassLb: Double,
    @Json(name = "first_flight") val firstFlight: String?,
    @Json(name = "heat_shield") val heatShield: HeatShieldDto,
    @Json(name = "thrusters") val thrusters: List<ThrusterDto>,
    @Json(name = "launch_payload_mass") val launchPayloadMass: MassDto,
    @Json(name = "launch_payload_vol") val launchPayloadVol: VolumeDto,
    @Json(name = "return_payload_mass") val returnPayloadMass: MassDto,
    @Json(name = "return_payload_vol") val returnPayloadVol: VolumeDto,
    @Json(name = "pressurized_capsule") val pressurizedCapsule: PressurizedCapsuleDto,
    @Json(name = "trunk") val trunk: TrunkDto,
    @Json(name = "height_w_trunk") val heightWTrunk: LengthDto,
    @Json(name = "diameter") val diameter: LengthDto,
    @Json(name = "wikipedia") val wikipdeia: String,
    @Json(name = "description") val description: String
)

@JsonClass(generateAdapter = true)
data class CargoDto(
    @Json(name = "solar_array") val solarArray: Int,
    @Json(name = "unpressurized_cargo") val unpressurizedCargo: Boolean
)

@JsonClass(generateAdapter = true)
data class HeatShieldDto(
    @Json(name = "material") val material: String,
    @Json(name = "size_meters") val sizeMeters: Double,
    @Json(name = "temp_degrees") val tempDegrees: Double,
    @Json(name = "dev_partner") val devPartner: String
)

@JsonClass(generateAdapter = true)
data class PressurizedCapsuleDto(
    @Json(name = "payload_volume") val payloadVol: VolumeDto
)

@JsonClass(generateAdapter = true)
data class ThrusterDto(
    @Json(name = "type") val type: String,
    @Json(name = "amount") val amount: Int,
    @Json(name = "pods") val pods: Int,
    @Json(name = "fuel_1") val fuel1: String,
    @Json(name = "fuel_2") val fuel2: String,
    @Json(name = "thrust") val thrust: ThrustDto
)

@JsonClass(generateAdapter = true)
data class TrunkDto(
    @Json(name = "trunk_volume") val trunkVolume: VolumeDto,
    @Json(name = "cargo") val cargo: CargoDto
)
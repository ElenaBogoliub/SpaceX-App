package com.ebogoliub.spacex.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ebogoliub.spacex.data.entities.common.Length
import com.ebogoliub.spacex.data.entities.common.Mass
import com.ebogoliub.spacex.data.entities.common.Thrust

@Entity
data class Rocket(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    override val id: Long = 0,
    @ColumnInfo(name = "active")
    val active: Boolean,
    @ColumnInfo(name = "stages")
    val stages: Int,
    @ColumnInfo(name = "boosters")
    val boosters: Int,
    @ColumnInfo(name = "cost_per_launch")
    val costPerLaunch: Long,
    @ColumnInfo(name = "success_rate_pct")
    val successRatePercentage: Double,
    @ColumnInfo(name = "first_flight")
    val firstFlight: String,
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "company")
    val company: String,
    @Embedded(prefix = "height_")
    val height: Length,
    @Embedded(prefix = "diameter_")
    val diameter: Length,
    @Embedded(prefix = "mass_")
    val mass: Mass,
    @Embedded(prefix = "first_stage_")
    val firstStage: FirstStage,
    @Embedded(prefix = "second_stage_")
    val secondStage: SecondStage,
    @Embedded(prefix = "engine_")
    val engines: Engine,
    @Embedded
    val landingLegs: LandingLegs,
    @ColumnInfo(name = "wikipedia")
    val wikipedia: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "rocket_name")
    val rocketName: String,
    @ColumnInfo(name = "rocket_type")
    val rocketType: String,
    @ColumnInfo(name = "rocket_id")
    val rocketId: String
) : SpacexEntity

data class FirstStage(
    val reusable: Boolean,
    val engines: Int,
    val fuelAmountTons: Double,
    val burnTimeSec: Double?,
    @Embedded(prefix = "thrust_sea_level_")
    val thrustSeaLevel: Thrust,
    @Embedded(prefix = "thrust_sea_vacuum_")
    val thrustVacuum: Thrust
)

data class SecondStage(
    val engines: Int,
    val fuelAmountTons: Double?,
    val burnTimeSec: Double?,
    @Embedded(prefix = "thrust_")
    val thrust: Thrust,
    @Embedded(prefix = "payloads_")
    val payloads: Payloads
)

data class Payloads(
    val option1: String?,
    val option2: String?,
    @Embedded(prefix = "composite_fairing_")
    val compositeFairing: CompositeFairing?
)

data class CompositeFairing(
    @Embedded(prefix = "height_")
    val height: Length,
    @Embedded(prefix = "diameter_")
    val diameter: Length
)

data class Engine(
    val number: Int,
    val type: String,
    val version: String,
    val layout: String?,
    val engineLossMax: Int?,
    val propellant1: String,
    val propellant2: String,
    @Embedded(prefix = "thrust_sea_level_")
    val thrustSeaLevel: Thrust,
    @Embedded(prefix = "thrust_vacuum_")
    val thrustVacuum: Thrust,
    val thrustToWeight: Double?
)

data class LandingLegs(
    val number: Int,
    val material: String?
)

package com.ebogoliub.spacex.data.mapper

import com.ebogoliub.spacex.api.common.LengthDto
import com.ebogoliub.spacex.api.common.MassDto
import com.ebogoliub.spacex.api.common.SecondStageDto
import com.ebogoliub.spacex.api.common.ThrustDto
import com.ebogoliub.spacex.api.rocket.*
import com.ebogoliub.spacex.data.entity.*
import com.ebogoliub.spacex.data.entity.common.Length
import com.ebogoliub.spacex.data.entity.common.Mass
import com.ebogoliub.spacex.data.entity.common.Thrust

internal fun RocketDto.toRocket(): Rocket = Rocket(
    rocketId = this.rocketId,
    rocketName = this.rocketName,
    rocketType = this.rocketType,
    id = this.id.toLong(),
    active = this.active,
    stages = this.stages,
    boosters = this.boosters,
    costPerLaunch = this.costPerLaunch,
    successRatePercentage = this.successRate,
    firstFlight = this.firstFlight,
    country = this.country,
    company = this.company,
    height = this.height.toDbLength(),
    diameter = this.diameter.toDbLength(),
    mass = this.mass.toDbMass(),
    firstStage = this.firstStage.toDbFirstStage(),
    secondStage = this.secondStage.toDbSecondStage(),
    engines = this.engines.toDbEngine(),
    landingLegs = this.landingLegs.toDbLandingLegs(),
    wikipedia = this.wikipedia,
    description = this.description
)

internal fun FirstStageDto.toDbFirstStage(): FirstStage = FirstStage(
    reusable = this.reusable,
    engines = this.engines,
    fuelAmountTons = this.fuelAmountTons,
    burnTimeSec = this.burnTimeSecs,
    thrustSeaLevel = this.thrustSeaLevel.toDbThrust(),
    thrustVacuum = this.thrustVacuum.toDbThrust()
)

internal fun CompositeFairingDto.toDbCompositeFairing(): CompositeFairing = CompositeFairing(
    height = this.height.toDbLength(),
    diameter = this.diameter.toDbLength()
)

internal fun PayloadsDto.toDbPayloads(): Payloads = Payloads(
    option1 = this.option1,
    option2 = this.option2,
    compositeFairing = this.compositeFairing.toDbCompositeFairing()
)

internal fun SecondStageDto.toDbSecondStage(): SecondStage = SecondStage(
    engines = this.engines,
    fuelAmountTons = this.fuelAmountTons,
    burnTimeSec = this.burnTimeSecs,
    thrust = this.thrust.toDbThrust(),
    payloads = this.payloads.toDbPayloads()
)

internal fun EnginesDto.toDbEngine(): Engine = Engine(
    number = this.number,
    type = this.type,
    version = this.version,
    layout = this.layout,
    engineLossMax = this.engineLossMax,
    propellant1 = this.propellant1,
    propellant2 = this.propellant2,
    thrustSeaLevel = this.thrustSeaLevel.toDbThrust(),
    thrustVacuum = this.thrustVacuum.toDbThrust(),
    thrustToWeight = this.thrustToWeightRatio
)

internal fun LandingLegsDto.toDbLandingLegs(): LandingLegs {
    return LandingLegs(
        number = this.number,
        material = this.material
    )
}

internal fun ThrustDto.toDbThrust(): Thrust {
    return Thrust(
        kN = this.kN,
        lbf = this.lbf
    )
}

internal fun LengthDto.toDbLength(): Length {
    return Length(
        metres = this.meters,
        feet = this.feet
    )
}

internal fun MassDto.toDbMass(): Mass {
    return Mass(
        kg = this.kg,
        lb = this.lb
    )
}
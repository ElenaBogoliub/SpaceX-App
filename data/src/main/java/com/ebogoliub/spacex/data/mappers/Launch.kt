package com.ebogoliub.spacex.data.mappers

import com.ebogoliub.spacex.api.launches.*
import com.ebogoliub.spacex.data.entities.*

internal fun LaunchDto.toLaunch(): Launch = Launch(
    id = flightNumber.toLong(),
    missionName = missionName,
    missionId = missionId,
    launchYear = launchYear,
    launchDateUtc = launchDate,
    isTentative = isTentative,
    tentativeMaxPrecision = tentativeMaxPrecision.toDatePrecision(),
    tbd = tbd,
    launchWindow = launchWindow?.toLong(),
    ships = ships,
    launchSuccess = launchSuccess,
    details = details,
    isUpcoming = upcoming,
    staticFireDateUtc = staticFireDate,
    telemetry = telemetryDto.toDbTelemetry(),
    launchSite = launchSiteDto.toDbLaunchSite(),
    links = linksDto.toDbLinks(),
    timeline = timelineDto?.toDbTimeline(),
    rocket = rocketDto.toDbLaunchRocket()
)

internal fun TelemetryDto.toDbTelemetry(): Telemetry {
    return Telemetry(flightClub)
}

internal fun LaunchSiteDto.toDbLaunchSite(): LaunchSite {
    return LaunchSite(id, name, nameLong)
}

internal fun LinksDto.toDbLinks(): Links {
    return Links(
        missionPatch,
        missionPatchSmall,
        redditCampaign,
        redditLaunch,
        redditRecovery,
        redditMedia,
        pressKit,
        article,
        wikipedia,
        video,
        youtubeKey,
        flickrImages
    )
}

internal fun TimelineDto.toDbTimeline(): Timeline {
    return Timeline(
        webcastLiftoff,
        goForPropLoading,
        rp1Loading,
        stage1LoxLoading,
        stage2LoxLoading,
        engineChill,
        prelaunchChecks,
        propellantPressurization,
        goForLaunch,
        ignition,
        liftoff,
        maxQ,
        meco,
        stageSeparation,
        secondStateIgnition,
        fairingDeploy,
        firstStageEntryBurn,
        seco1,
        fistStageLanding,
        secondStageRestart,
        seco2,
        payloadDeploy

    )
}

internal fun RocketSummaryDto.toDbLaunchRocket(): LaunchRocket {
    return LaunchRocket(
        rocketId = this.rocketId,
        rocketName = this.name,
        rocketType = this.type,
        fairings = this.fairingDto?.toDbFairings()
    )
}

internal fun FairingDto.toDbFairings(): Fairings {
    return Fairings(
        reused = this.reused,
        recovered = this.recovered,
        recoveryAttempt = this.recoveryAttempt,
        ship = this.ship
    )
}
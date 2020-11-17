package com.ebogoliub.spacex.api.launches

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.Instant
import org.threeten.bp.ZonedDateTime

@JsonClass(generateAdapter = true)
data class LaunchDto(
    @Json(name = "flight_number") val flightNumber: Int,
    @Json(name = "mission_name") val missionName: String,
    @Json(name = "mission_id") val missionId: List<String>,
    @Json(name = "launch_year") val launchYear: String,
    @Json(name = "launch_date_utc") val launchDate: ZonedDateTime,
    @Json(name = "is_tentative") val isTentative: Boolean,
    @Json(name = "tentative_max_precision") val tentativeMaxPrecision: String?,
    @Json(name = "tbd") val tbd: Boolean,
    @Json(name = "launch_window") val launchWindow: Int?,
    @Json(name = "rocket") val rocketDto: RocketSummaryDto,
    @Json(name = "ships") val ships: List<String>,
    @Json(name = "telemetry") val telemetryDto: TelemetryDto,
    @Json(name = "launch_site") val launchSiteDto: LaunchSiteDto,
    @Json(name = "launch_success") val launchSuccess: Boolean?,
    @Json(name = "links") val linksDto: LinksDto,
    @Json(name = "details") val details: String?,
    @Json(name = "upcoming") val upcoming: Boolean?,
    @Json(name = "static_fire_date_utc") val staticFireDate: ZonedDateTime?,
    @Json(name = "timeline") val timelineDto: TimelineDto?
)

@JsonClass(generateAdapter = true)
data class TimelineDto(
    @Json(name = "webcast_liftoff") val webcastLiftoff: Int?,
    @Json(name = "go_for_prop_loading") val goForPropLoading: Int?,
    @Json(name = "rp1_loading") val rp1Loading: Int?,
    @Json(name = "stage1_lox_loading") val stage1LoxLoading: Int?,
    @Json(name = "stage2_lox_loading") val stage2LoxLoading: Int?,
    @Json(name = "engine_chill") val engineChill: Int?,
    @Json(name = "prelaunch_checks") val prelaunchChecks: Int?,
    @Json(name = "propellant_pressurization") val propellantPressurization: Int?,
    @Json(name = "go_for_launch") val goForLaunch: Int?,
    @Json(name = "ignition") val ignition: Int?,
    @Json(name = "liftoff") val liftoff: Int?,
    @Json(name = "maxq") val maxQ: Int?,
    @Json(name = "meco") val meco: Int?,
    @Json(name = "stage_sep") val stageSeparation: Int?,
    @Json(name = "second_stage_ignition") val secondStateIgnition: Int?,
    @Json(name = "fairing_deploy") val fairingDeploy: Int?,
    @Json(name = "first_stage_entry_burn") val firstStageEntryBurn: Int?,
    @Json(name = "seco-1") val seco1: Int?,
    @Json(name = "first_stage_landing") val fistStageLanding: Int?,
    @Json(name = "second_stage_restart") val secondStageRestart: Int?,
    @Json(name = "seco-2") val seco2: Int?,
    @Json(name = "payload_deploy") val payloadDeploy: Int?
)

@JsonClass(generateAdapter = true)
data class TelemetryDto(
    @Json(name = "flight_club") val flightClub: String?
)

@JsonClass(generateAdapter = true)
data class LinksDto(
    @Json(name = "mission_patch") val missionPatch: String?,
    @Json(name = "mission_patch_small") val missionPatchSmall: String?,
    @Json(name = "reddit_campaign") val redditCampaign: String?,
    @Json(name = "reddit_launch") val redditLaunch: String?,
    @Json(name = "reddit_recovery") val redditRecovery: String?,
    @Json(name = "reddit_media") val redditMedia: String?,
    @Json(name = "presskit") val pressKit: String?,
    @Json(name = "article_link") val article: String?,
    @Json(name = "wikipedia") val wikipedia: String?,
    @Json(name = "video_link") val video: String?,
    @Json(name = "youtube_id") val youtubeKey: String?,
    @Json(name = "flickr_images") val flickrImages: List<String>
)

@JsonClass(generateAdapter = true)
data class LaunchSiteDto(
    @Json(name = "site_id") val id: String?,
    @Json(name = "site_name") val name: String?,
    @Json(name = "site_name_long") val nameLong: String?
)

@JsonClass(generateAdapter = true)
data class RocketSummaryDto(
    @Json(name = "rocket_id") val rocketId: String,
    @Json(name = "rocket_name") val name: String,
    @Json(name = "rocket_type") val type: String,
    @Json(name = "first_stage") val firstStage: FirstStageSummary,
    @Json(name = "second_stage") val secondState: SecondStageSummary,
    @Json(name = "fairings") val fairingDto: FairingDto?
)

@JsonClass(generateAdapter = true)
data class SecondStageSummary(
    @Json(name = "block") val block: Int?,
    @Json(name = "payloads") val payloads: List<Payload>
)

@JsonClass(generateAdapter = true)
data class FirstStageSummary(
    @Json(name = "cores") val cores: List<CoreSummary>
)

@JsonClass(generateAdapter = true)
data class FairingDto(
    @Json(name = "reused") val reused: Boolean?,
    @Json(name = "recovery_attempt") val recoveryAttempt: Boolean?,
    @Json(name = "recovered") val recovered: Boolean?,
    @Json(name = "ship") val ship: String?
)

@JsonClass(generateAdapter = true)
data class CoreSummary(
    @Json(name = "core_serial") val serial: String?,
    @Json(name = "flight") val flight: Int?,
    @Json(name = "block") val block: Int?,
    @Json(name = "gridfins") val gridfins: Boolean?,
    @Json(name = "legs") val legs: Boolean?,
    @Json(name = "reused") val reused: Boolean?,
    @Json(name = "land_success") val landSuccess: Boolean?,
    @Json(name = "landing_intent") val landingIntent: Boolean?,
    @Json(name = "landing_type") val landingType: String?,
    @Json(name = "landing_vehicle") val landingVehicle: String?
)
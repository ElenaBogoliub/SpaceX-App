package com.ebogoliub.spacex.data.entities

import androidx.room.*
import org.threeten.bp.ZonedDateTime

@Entity
data class Launch(
    @PrimaryKey
    @ColumnInfo(name = "flight_number")
    override val id: Long = 0,
    @ColumnInfo(name = "mission_name")
    val missionName: String,
    @ColumnInfo(name = "mission_id")
    val missionId: List<String>,
    @ColumnInfo(name = "launch_year")
    val launchYear: String,
    @ColumnInfo(name = "launch_date_utc")
    val launchDateUtc: ZonedDateTime,
    @ColumnInfo(name = "is_tentative")
    val isTentative: Boolean,
    @ColumnInfo(name = "tentative_max_precision")
    val tentativeMaxPrecision: DatePrecision,
    @ColumnInfo(name = "tbd")
    val tbd: Boolean,
    @ColumnInfo(name = "launch_window")
    val launchWindow: Long?,
    @Embedded
    val rocket: LaunchRocket,
    @ColumnInfo(name = "ships")
    val ships: List<String>,
    @Embedded
    val telemetry: Telemetry?,
    @Embedded
    val launchSite: LaunchSite?,
    @ColumnInfo(name = "launch_success")
    val launchSuccess: Boolean?,
    @Embedded
    val links: Links?,
    @Embedded
    val timeline: Timeline?,
    @ColumnInfo(name = "details")
    val details: String?,
    @ColumnInfo(name = "upcoming")
    val isUpcoming: Boolean?,
    @ColumnInfo(name = "static_fire_date_utc")
    val staticFireDateUtc: ZonedDateTime?
) : SpacexEntity

data class LaunchRocket(
    @ColumnInfo(name = "rocket_id")
    val rocketId: String,
    @ColumnInfo(name = "rocket_name")
    val rocketName: String,
    @ColumnInfo(name = "rocket_type")
    val rocketType: String,
    @Embedded
    val fairings: Fairings?
)

data class Fairings(
    val reused: Boolean?,
    val recoveryAttempt: Boolean?,
    val recovered: Boolean?,
    val ship: String?
)

data class Telemetry(
    val flightClub: String?
)

data class LaunchSite(
    val siteId: String?,
    val siteName: String?,
    val siteNameLong: String?
)

data class Links(
    val missionPatch: String?,
    val missionPatchSmall: String?,
    val redditCampaign: String?,
    val redditLaunch: String?,
    val redditRecovery: String?,
    val redditMedia: String?,
    val presskit: String?,
    val article: String?,
    val wikipedia: String?,
    val video: String?,
    val youtubeKey: String?,
    val flickrImages: List<String>?
)

data class Timeline(
    val webcastLiftoff: Int?,
    val goForPropLoading: Int?,
    val rp1Loading: Int?,
    val stage1LoxLoading: Int?,
    val stage2LoxLoading: Int?,
    val engineChill: Int?,
    val prelaunchChecks: Int?,
    val propellantPressurization: Int?,
    val goForLaunch: Int?,
    val ignition: Int?,
    val liftoff: Int?,
    val maxq: Int?,
    val meco: Int?,
    val stageSeparation: Int?,
    val secondStageIgnition: Int?,
    val fairingDeploy: Int?,
    val firstStageEntryBurn: Int?,
    val seco1: Int?,
    val firstStageLanding: Int?,
    val secondStageRestart: Int?,
    val seco2: Int?,
    val payloadDeploy: Int?
)

data class LaunchPictures(
    val flickrImages: List<String>?
)

fun Launch.missionPatch(small: Boolean = false): String? {
    return if (small) {
        links?.missionPatchSmall
    } else {
        links?.missionPatch
    }
}
package com.ebogoliub.spacex.api.launches

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LaunchesService {

    @GET("launches")
    suspend fun getAllLaunches(
        @Query("flight_id") flightId: String? = null,
        @Query("start") start: String? = null,
        @Query("end") end: String? = null,
        @Query("land_success") landSucess: Boolean? = null,
        @Query("site_id") siteId: String? = null,
        @Query("customer") customer: String? = null,
        @Query("nationality") nationality: String? = null,
        @Query("launch_success") launchSuccess: Boolean? = null,
        @Query("limit") limit: Int? = null,
        @Query("sort") sort: String = "original_launch",
        @Query("order") order: String = "desc"
    ): List<LaunchDto>

    @GET("launches/{flightNumber}")
    suspend fun getLaunch(@Path("flightNumber") flightNumber: Int): LaunchDto

    @GET("launches/past")
    suspend fun getPastLaunches(
        @Query("flight_id") flightId: String? = null,
        @Query("start") start: String? = null,
        @Query("end") end: String? = null,
        @Query("land_success") landSucess: Boolean? = null,
        @Query("site_id") siteId: String? = null,
        @Query("customer") customer: String? = null,
        @Query("nationality") nationality: String? = null,
        @Query("launch_success") launchSuccess: Boolean? = null,
        @Query("limit") limit: Int? = null,
        @Query("sort") sort: String = "original_launch",
        @Query("order") order: String = "desc"
    ): List<LaunchDto>

    @GET("launches/upcoming")
    suspend fun getUpcomingLaunches(
        @Query("flight_id") flightId: String? = null,
        @Query("start") start: String? = null,
        @Query("end") end: String? = null,
        @Query("land_success") landSucess: Boolean? = null,
        @Query("site_id") siteId: String? = null,
        @Query("customer") customer: String? = null,
        @Query("nationality") nationality: String? = null,
        @Query("launch_success") launchSuccess: Boolean? = null,
        @Query("limit") limit: Int? = null,
        @Query("sort") sort: String = "original_launch",
        @Query("order") order: String = "asc"
    ): List<LaunchDto>

    @GET("launches/latest")
    suspend fun getLatestLaunch(): LaunchDto

    @GET("launches/next")
    suspend fun getNextLaunch(): LaunchDto
}
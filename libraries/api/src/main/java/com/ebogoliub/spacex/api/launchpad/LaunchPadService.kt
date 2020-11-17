package com.ebogoliub.spacex.api.launchpad

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LaunchPadService {

    @GET("launchpads")
    suspend fun getAllLaunchPads(
        @Query("limit") limit: Int? = null
    ): List<LaunchPadDto>

    @GET("launchpads/{siteId}")
    suspend fun getLaunchPad(@Path("siteId") siteId: String): LaunchPadDto
}
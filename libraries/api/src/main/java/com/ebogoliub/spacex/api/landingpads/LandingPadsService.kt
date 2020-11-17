package com.ebogoliub.spacex.api.landingpads

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LandingPadsService {

    @GET("landpads")
    suspend fun getAllLandingPads(
        @Query("limit") limit: Int? = null
    ): List<LandingPadDto>

    @GET("landpads/{id}")
    suspend fun getLandingPad(@Path("id") id: Int): LandingPadDto
}
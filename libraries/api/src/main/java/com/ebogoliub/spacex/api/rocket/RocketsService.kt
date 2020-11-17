package com.ebogoliub.spacex.api.rocket

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RocketsService {

    @GET("rockets")
    suspend fun getAllRockets(
        @Query("limit") limit: Int? = null
    ): List<RocketDto>

    @GET("rockets/{rocketId}")
    suspend fun getRocket(@Path("rocketId") rocketId: String): RocketDto
}
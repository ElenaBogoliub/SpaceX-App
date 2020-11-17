package com.ebogoliub.spacex.api.dragon

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DragonsService {

    @GET("dragons")
    suspend fun getAllDragons(
        @Query("limit") limit: Int? = null
    ): List<DragonDto>

    @GET("dragons/{id}")
    suspend fun getDragon(@Path("id") id: String): DragonDto
}
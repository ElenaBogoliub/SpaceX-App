package com.ebogoliub.spacex.api.mission

import retrofit2.http.GET
import retrofit2.http.Path

interface MissionService {

    @GET("missions")
    suspend fun getAllMissions(): List<MissionDto>

    @GET("missions/{missionId}")
    suspend fun getMission(@Path("missionId") missionId: String): MissionDto
}
package com.ebogoliub.spacex.api.capsule

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Date

interface CapsuleService {
    @GET("capsules")
    suspend fun getAllCapsules(
        @Query("status") status: String? = null,
        @Query("original_launch") originalLaunch: Date? = null,
        @Query("missions") mission: String? = null,
        @Query("landings") landings: Int? = null,
        @Query("type") type: String? = null,
        @Query("reuse_count") reuseCount: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("sort") sort: String = "original_launch",
        @Query("order") order: String = "desc"
    ): List<CapsuleDto>

    @GET("capsules/{capsuleSerial}")
    suspend fun getCapsule(@Path("capsuleSerial") serial: String): CapsuleDto

    @GET("capsules/upcoming")
    suspend fun getUpcomingCapsules(
        @Query("status") status: String? = null,
        @Query("original_launch") originalLaunch: Date? = null,
        @Query("missions") mission: String? = null,
        @Query("landings") landings: Int? = null,
        @Query("type") type: String? = null,
        @Query("reuse_count") reuseCount: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("sort") sort: String = "original_launch",
        @Query("order") order: String = "desc"
    ): List<CapsuleDto>

    @GET("capsules/past")
    suspend fun getPastCapsules(
        @Query("status") status: String? = null,
        @Query("original_launch") originalLaunch: Date? = null,
        @Query("missions") mission: String? = null,
        @Query("landings") landings: Int? = null,
        @Query("type") type: String? = null,
        @Query("reuse_count") reuseCount: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("sort") sort: String = "original_launch",
        @Query("order") order: String = "desc"
    ): List<CapsuleDto>
}
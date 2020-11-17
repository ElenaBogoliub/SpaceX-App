package com.ebogoliub.spacex.api.history

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HistoryService {

    @GET("history")
    suspend fun getAllHistoricalEvents(
        @Query("start") start: String? = null,
        @Query("end") end: String? = null,
        @Query("flight_number") flightNumber: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("sort") sort: String = "flight_number",
        @Query("order") order: String = "desc"
    ): List<HistoricalEventDto>

    @GET("history/{id}")
    suspend fun getHistoricalEvent(@Path("id") id: Int): HistoricalEventDto
}
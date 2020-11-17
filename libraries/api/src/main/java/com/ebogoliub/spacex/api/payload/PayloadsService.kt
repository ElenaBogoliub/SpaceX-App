package com.ebogoliub.spacex.api.payload

import com.ebogoliub.spacex.api.launches.Payload
import retrofit2.http.GET
import retrofit2.http.Path

interface PayloadsService {

    @GET("payloads")
    suspend fun getAllPayloads(): List<Payload>

    @GET("payloads/{payloadId}")
    suspend fun getPayload(@Path("payloadId") payloadId: String): Payload
}
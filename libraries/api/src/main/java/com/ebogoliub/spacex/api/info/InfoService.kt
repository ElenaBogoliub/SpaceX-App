package com.ebogoliub.spacex.api.info

import retrofit2.http.GET

interface InfoService {

    @GET("info")
    suspend fun getSpacexInfo(): CompanyInfoDto
}
package com.ebogoliub.spacex.data.repositories

import com.ebogoliub.spacex.api.rocket.RocketsService
import com.ebogoliub.spacex.core.DataStoreFactory
import com.ebogoliub.spacex.core.Fetcher
import com.ebogoliub.spacex.core.FetcherResult
import com.ebogoliub.spacex.core.SourceOfTruth
import com.ebogoliub.spacex.data.daos.RocketsDao
import com.ebogoliub.spacex.data.entities.Rocket
import com.ebogoliub.spacex.data.mappers.toRocket
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RocketsRepository @Inject constructor(
    private val rocketsDao: RocketsDao,
    private val rocketsService: RocketsService
) {

    suspend fun getRocket(): Flow<FetcherResult<List<Rocket>>> {
        return DataStoreFactory.from(
            fetcher = Fetcher.of { rocketsService.getAllRockets().map {  it.toRocket() } },
            sourceOfTruth = SourceOfTruth.of(
                reader = { rocketsDao.all() },
                writer = { launches, _ ->
                    rocketsDao.clearAndInsert(launches)
                },
                cacheValidator = { data, _ -> !data.isNullOrEmpty() }
            )
        ).stream()
    }
}
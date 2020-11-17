package com.ebogoliub.spacex.data.repositories

import com.ebogoliub.spacex.api.launches.LaunchesService
import com.ebogoliub.spacex.core.DataStoreFactory
import com.ebogoliub.spacex.core.Fetcher
import com.ebogoliub.spacex.core.FetcherResult
import com.ebogoliub.spacex.core.SourceOfTruth
import com.ebogoliub.spacex.data.daos.LaunchDao
import com.ebogoliub.spacex.data.entities.Launch
import com.ebogoliub.spacex.data.mappers.toLaunch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NextLaunchRepository @Inject constructor(
    private val launchDao: LaunchDao,
    private val launchesService: LaunchesService
) {

    suspend fun getNextLaunch(): Flow<FetcherResult<Launch>> {
        return DataStoreFactory.from(
            fetcher = Fetcher.of { launchesService.getNextLaunch().toLaunch() },
            sourceOfTruth = SourceOfTruth.of(
                reader = { launchDao.next() },
                writer = { launch, _ ->
                    launchDao.insert(launch)
                },
                cacheValidator = { _, _ -> true }
            )
        ).stream()
    }
}
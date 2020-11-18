package com.ebogoliub.spacex.data.repository

import com.ebogoliub.spacex.api.launches.LaunchesService
import com.ebogoliub.spacex.core.DataStoreFactory
import com.ebogoliub.spacex.core.Fetcher
import com.ebogoliub.spacex.core.FetcherResult
import com.ebogoliub.spacex.core.SourceOfTruth
import com.ebogoliub.spacex.data.dao.LaunchDao
import com.ebogoliub.spacex.data.entity.Launch
import com.ebogoliub.spacex.data.mapper.toLaunch
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
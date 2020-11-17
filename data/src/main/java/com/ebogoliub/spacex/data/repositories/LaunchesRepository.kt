package com.ebogoliub.spacex.data.repositories

import com.ebogoliub.spacex.api.launches.LaunchesService
import com.ebogoliub.spacex.core.*
import com.ebogoliub.spacex.data.daos.LaunchDao
import com.ebogoliub.spacex.data.entities.Launch
import com.ebogoliub.spacex.data.mappers.toLaunch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LaunchesRepository @Inject constructor(
    private val launchDao: LaunchDao,
    private val launchesService: LaunchesService
) {

    private val store = DataStoreFactory.from(
        fetcher = Fetcher.of { launchesService.getAllLaunches().map { it.toLaunch() } },
        sourceOfTruth = SourceOfTruth.of(
            reader = { params ->
                params as LaunchesFetcherParams
                launchDao.all(params.limit, params.offset)
            },
            writer = { launches, _ ->
                launchDao.clearAndInsert(launches)
            },
            cacheValidator = { cashedData, params ->
                params as LaunchesFetcherParams
                return@of cashedData.size > 1 || params.offset != 0
            }
        )
    )

    suspend fun getLaunches(
        offset: Int,
        refresh: Boolean = false,
        limit: Int = DEFAULT_LIMIT
    ): Flow<FetcherResult<List<Launch>>> {
        return store.stream(FetcherParams(LaunchesFetcherParams(limit, offset), refresh))
    }

    data class LaunchesFetcherParams(val limit: Int, val offset: Int)

    companion object {
        const val DEFAULT_LIMIT = 20
    }
}

enum class LaunchType {
    All, Recent, Upcoming
}
package com.ebogoliub.spacex.data.repository

import com.ebogoliub.spacex.api.launches.LaunchesService
import com.ebogoliub.spacex.core.*
import com.ebogoliub.spacex.data.dao.LaunchDao
import com.ebogoliub.spacex.data.entity.Launch
import com.ebogoliub.spacex.data.mapper.toLaunch
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
            cacheValidator = { cachedData, params ->
                params as LaunchesFetcherParams
                return@of cachedData.size > 1 || params.offset != 0
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
    ALL, RECENT, UPCOMING
}
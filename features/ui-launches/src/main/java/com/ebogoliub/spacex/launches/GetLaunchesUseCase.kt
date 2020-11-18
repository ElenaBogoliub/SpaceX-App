package com.ebogoliub.spacex.launches

import com.ebogoliub.spacex.core.FetcherResult
import com.ebogoliub.spacex.data.entity.Launch
import com.ebogoliub.spacex.data.repository.LaunchesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLaunchesUseCase  @Inject constructor(
    private val launchesRepository: LaunchesRepository
) {
    suspend operator fun invoke(offset: Int, refresh: Boolean): Flow<FetcherResult<List<Launch>>> {
        return launchesRepository.getLaunches(offset, refresh)
    }
}
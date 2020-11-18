package com.ebogoliub.spacex.nextlaunch

import com.ebogoliub.spacex.core.FetcherResult
import com.ebogoliub.spacex.data.entity.Launch
import com.ebogoliub.spacex.data.repository.NextLaunchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNextLaunchUseCase @Inject constructor(
    private val nextLaunchRepository: NextLaunchRepository
) {

    suspend operator fun invoke(): Flow<FetcherResult<Launch>> = nextLaunchRepository.getNextLaunch()
}
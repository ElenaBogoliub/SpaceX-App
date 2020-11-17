package com.ebogoliub.spacex.ui.rockets

import com.ebogoliub.spacex.core.FetcherResult
import com.ebogoliub.spacex.data.entities.Rocket
import com.ebogoliub.spacex.data.repositories.RocketsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RocketsUseCase @Inject constructor(
    private val rocketsRepository: RocketsRepository
) {

    suspend operator fun invoke(): Flow<FetcherResult<List<Rocket>>> = rocketsRepository.getRocket()
}
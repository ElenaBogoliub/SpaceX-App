package com.ebogoliub.spacex.ui.rockets

import androidx.hilt.lifecycle.ViewModelInject
import com.ebogoliub.flowmvi.EffectHandler
import com.ebogoliub.flowmvi.StateReducer
import com.ebogoliub.flowmvi.StateWithEffects
import com.ebogoliub.spacex.core.FetcherResult
import com.ebogoliub.spacex.core.CoroutinesDispatchers
import com.ebogoliub.spacex.ui.mvvm.BaseMviViewModel
import com.ebogoliub.ui.base.content.ErrorMessageProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class RocketsViewModel @ViewModelInject constructor(
    private val rocketsUseCase: RocketsUseCase,
    private val errorMessageProvider: ErrorMessageProvider,
    coroutinesDispatchers: CoroutinesDispatchers
) : BaseMviViewModel<RocketsState, RocketsAction, RocketsEffect, RocketsPartialState>(
    RocketsState(),
    InitAction,
    coroutinesDispatchers
) {

    override fun getStateReducer() = object : StateReducer<RocketsAction, RocketsState,
            RocketsEffect, RocketsPartialState> {
        override fun reduce(
            state: RocketsState,
            partialState: RocketsPartialState
        ): StateWithEffects<RocketsState, RocketsEffect> {
            return when (partialState) {
                is LoadingPartialState -> {
                    StateWithEffects(
                        state.copy(isLoading = true), listOf(LoadEffect)
                    )
                }
                is LoadedPartialState -> StateWithEffects(
                    state.copy(items = partialState.items, isLoading = false)
                )
                is ErrorPartialState -> StateWithEffects(
                    state.copy(
                        emptyState = errorMessageProvider.getErrorState(partialState.error),
                        isLoading = false
                    )
                )
            }
        }

        override fun partial(
            state: RocketsState,
            event: RocketsAction
        ): RocketsPartialState {
            return when (event) {
                InitAction -> LoadingPartialState
            }
        }
    }

    override fun getEffectHandler() =
        object : EffectHandler<RocketsEffect, RocketsState, RocketsPartialState> {
            override fun handle(
                effects: Flow<RocketsEffect>
            ): Flow<RocketsPartialState> {
                return effects
                    .filterIsInstance<LoadEffect>()
                    .flatMapLatest { rocketsUseCase() }
                    .map { result ->
                        when (result) {
                            is FetcherResult.Data -> LoadedPartialState(result.value)
                            is FetcherResult.Error -> ErrorPartialState(result.error)
                        }
                    }
            }
        }
}
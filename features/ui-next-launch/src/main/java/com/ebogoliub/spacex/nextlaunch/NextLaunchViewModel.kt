package com.ebogoliub.spacex.nextlaunch

import androidx.hilt.lifecycle.ViewModelInject
import com.ebogoliub.flowmvi.EffectHandler
import com.ebogoliub.flowmvi.StateReducer
import com.ebogoliub.flowmvi.StateWithEffects
import com.ebogoliub.spacex.core.FetcherResult
import com.ebogoliub.spacex.core.di.CoroutinesDispatchers
import com.ebogoliub.spacex.ui.mvvm.BaseMviViewModel
import com.ebogoliub.ui.base.content.ErrorMessageProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class NextLaunchViewModel @ViewModelInject constructor(
    private val nextLaunchUseCase: GetNextLaunchUseCase,
    private val errorMessageProvider: ErrorMessageProvider,
    coroutinesDispatchers: CoroutinesDispatchers
) : BaseMviViewModel<NextLaunchState, NextLaunchAction, NextLaunchEffect, NextLaunchPartialState>(
    NextLaunchState(),
    InitAction,
    coroutinesDispatchers
) {

    override fun getStateReducer() = object : StateReducer<NextLaunchAction, NextLaunchState,
            NextLaunchEffect, NextLaunchPartialState> {
        override fun reduce(
            state: NextLaunchState,
            partialState: NextLaunchPartialState
        ): StateWithEffects<NextLaunchState, NextLaunchEffect> {
            return when (partialState) {
                is LoadingPartialState -> {
                    StateWithEffects(
                        state.copy(isLoading = true), listOf(LoadEffect)
                    )
                }
                is LoadedPartialState -> StateWithEffects(
                    state.copy(launch = partialState.launch, isLoading = false)
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
            state: NextLaunchState,
            event: NextLaunchAction
        ): NextLaunchPartialState {
            return when (event) {
                InitAction -> LoadingPartialState
            }
        }
    }

    override fun getEffectHandler() =
        object : EffectHandler<NextLaunchEffect, NextLaunchState, NextLaunchPartialState> {
            override fun handle(
                effects: Flow<NextLaunchEffect>
            ): Flow<NextLaunchPartialState> {
                return effects
                    .filterIsInstance<LoadEffect>()
                    .flatMapLatest { nextLaunchUseCase() }
                    .map { result ->
                        when (result) {
                            is FetcherResult.Data -> LoadedPartialState(result.value)
                            is FetcherResult.Error -> ErrorPartialState(result.error)
                        }
                    }
            }
        }
}
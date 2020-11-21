package com.ebogoliub.spacex.launches

import androidx.hilt.lifecycle.ViewModelInject
import com.ebogoliub.flowmvi.EffectHandler
import com.ebogoliub.flowmvi.StateReducer
import com.ebogoliub.flowmvi.StateWithEffects
import com.ebogoliub.spacex.core.FetcherResult
import com.ebogoliub.spacex.core.CoroutinesDispatchers
import com.ebogoliub.spacex.core.extentions.hasAtLeastSize
import com.ebogoliub.spacex.data.repository.LaunchesRepository.Companion.DEFAULT_LIMIT
import com.ebogoliub.spacex.ui.mvvm.BaseMviViewModel
import com.ebogoliub.ui.base.content.ErrorMessageProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class LaunchesViewModel @ViewModelInject constructor(
    private val launchesUseCase: GetLaunchesUseCase,
    private val errorMessageProvider: ErrorMessageProvider,
    coroutinesDispatchers: CoroutinesDispatchers
) : BaseMviViewModel<LaunchesState, LaunchesAction, LaunchesEffect, LaunchesPartialState>(
    LaunchesState(),
    InitAction,
    coroutinesDispatchers
) {

    override fun getStateReducer() = object : StateReducer<LaunchesAction, LaunchesState,
            LaunchesEffect, LaunchesPartialState> {
        override fun reduce(
            state: LaunchesState,
            partialState: LaunchesPartialState
        ): StateWithEffects<LaunchesState, LaunchesEffect> {
            return when (partialState) {
                is LoadingPartialState -> {
                    StateWithEffects(
                        state.copy(isLoading = true), listOf(LoadEffect(state.offset))
                    )
                }
                is LoadedPartialState -> {
                    val items = if (state.isLoadingMore) state.items + partialState.items else partialState.items
                    StateWithEffects(
                        state.copy(
                            items = items,
                            hasMore = items.hasAtLeastSize(DEFAULT_LIMIT),
                            isLoading = false,
                            isLoadingMore = false,
                            isRefreshing = false
                        )
                    )
                }
                is LoadErrorPartialState -> {
                    StateWithEffects(
                        state.copy(
                            emptyState = errorMessageProvider.getErrorState(partialState.error),
                            isLoading = false
                        )
                    )
                }
                LoadMorePartialState -> {
                    StateWithEffects(
                        state.copy(isLoadingMore = true), listOf(LoadEffect(state.offset))
                    )
                }
                ReloadingPartialState -> {
                    StateWithEffects(
                        state.copy(isRefreshing = true),
                        listOf(LoadEffect(0, true))
                    )
                }
                ReloadedPartialState -> {
                    StateWithEffects(state.copy(isRefreshing = false))
                }
                is ReloadErrorPartialState -> {
                    viewEvents.postValue(
                        ShowToast(errorMessageProvider.getErrorMessage(partialState.error))
                    )
                    StateWithEffects(state.copy(isRefreshing = false))
                }
            }
        }

        override fun partial(
            state: LaunchesState,
            event: LaunchesAction
        ): LaunchesPartialState {
            return when (event) {
                InitAction -> LoadingPartialState
                LoadMoreAction -> LoadMorePartialState
                ReloadAction -> ReloadingPartialState
            }
        }
    }

    override fun getEffectHandler() =
        object : EffectHandler<LaunchesEffect, LaunchesState, LaunchesPartialState> {
            override fun handle(
                effects: Flow<LaunchesEffect>
            ): Flow<LaunchesPartialState> {
                return effects
                    .filterIsInstance<LoadEffect>()
                    .flatMapLatest { effect ->
                        launchesUseCase(effect.offset, effect.refresh)
                    }
                    .map { result ->
                        when (result) {
                            is FetcherResult.Data -> LoadedPartialState(result.value)
                            is FetcherResult.Error -> LoadErrorPartialState(result.error)
                        }
                    }
            }
        }
}
package com.ebogoliub.spacex.launches

import com.ebogoliub.spacex.data.entities.Launch
import com.ebogoliub.spacex.data.repositories.LaunchType
import com.ebogoliub.spacex.ui.livedata.SingleLiveEvent
import com.ebogoliub.ui.base.content.EmptyState
import com.ebogoliub.ui.base.content.StringSource

data class LaunchesState(
    val filter: LaunchType = LaunchType.Recent,
    val items: List<Launch> = emptyList(),
    val hasMore: Boolean = true,
    val isLoading: Boolean = true,
    val isLoadingMore: Boolean = false,
    val isRefreshing: Boolean = false,
    val emptyState: EmptyState? = null
) {
    val offset: Int = items.size
}

sealed class LaunchesAction
object InitAction : LaunchesAction()
object LoadMoreAction : LaunchesAction()
object ReloadAction : LaunchesAction()

sealed class LaunchesEffect
data class LoadEffect(val offset: Int, val refresh: Boolean = false) : LaunchesEffect()

sealed class LaunchesPartialState
data class LoadedPartialState(val items: List<Launch>) : LaunchesPartialState()
object ReloadedPartialState : LaunchesPartialState()
object LoadingPartialState : LaunchesPartialState()
object LoadMorePartialState : LaunchesPartialState()
object ReloadingPartialState : LaunchesPartialState()
data class LoadErrorPartialState(val error: Throwable) : LaunchesPartialState()
data class ReloadErrorPartialState(val error: Throwable) : LaunchesPartialState()

data class ShowToast(val message: StringSource) : SingleLiveEvent
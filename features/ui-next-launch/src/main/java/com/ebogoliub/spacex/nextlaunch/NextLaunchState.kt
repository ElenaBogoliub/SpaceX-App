package com.ebogoliub.spacex.nextlaunch

import com.ebogoliub.spacex.data.entities.Launch
import com.ebogoliub.ui.base.content.EmptyState

data class NextLaunchState(
    val launch: Launch? = null,
    val emptyState: EmptyState? = null,
    val isLoading: Boolean = false
)
sealed class NextLaunchAction
object InitAction : NextLaunchAction()

sealed class NextLaunchEffect
object LoadEffect : NextLaunchEffect()

sealed class NextLaunchPartialState
data class LoadedPartialState(val launch: Launch) : NextLaunchPartialState()
data class ErrorPartialState(val error: Throwable) : NextLaunchPartialState()
object LoadingPartialState : NextLaunchPartialState()
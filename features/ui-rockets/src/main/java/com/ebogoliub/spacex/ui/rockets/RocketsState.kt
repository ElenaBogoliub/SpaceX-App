package com.ebogoliub.spacex.ui.rockets

import com.ebogoliub.spacex.data.entity.Rocket
import com.ebogoliub.ui.base.content.EmptyState

data class RocketsState(
    val items: List<Rocket> = emptyList(),
    val emptyState: EmptyState? = null,
    val isLoading: Boolean = false
)
sealed class RocketsAction
object InitAction : RocketsAction()

sealed class RocketsEffect
object LoadEffect : RocketsEffect()

sealed class RocketsPartialState
data class LoadedPartialState(val items: List<Rocket>) : RocketsPartialState()
data class ErrorPartialState(val error: Throwable) : RocketsPartialState()
object LoadingPartialState : RocketsPartialState()
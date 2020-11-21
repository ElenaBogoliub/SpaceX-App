package com.ebogoliub.flowmvi

import kotlinx.coroutines.flow.Flow


data class StateWithEffects<out State : Any, out Effect : Any>(
    val state: State,
    val effects: List<Effect> = emptyList()
)

interface StateReducer<Event : Any, State : Any, Effect : Any, PartialState : Any> {
    fun reduce(state: State, partialState: PartialState): StateWithEffects<State, Effect>
    fun partial(state: State, event: Event): PartialState
}

interface EffectHandler<SideEffect : Any, State : Any, PartialState : Any> {
    fun handle(effects: Flow<SideEffect>): Flow<PartialState>
}
package com.ebogoliub.flowmvi

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class MviStore<State : Any, Action : Any, SideEffect : Any, PartialState : Any>(
    private val stateReducer: StateReducer<Action, State, SideEffect, PartialState>,
    private val effectHandler: EffectHandler<SideEffect, State, PartialState>? = null,
    private val listener: Listener<Action, State, SideEffect, PartialState>? = null,
    private val coroutinesDispatchers: CoroutinesDispatchersProvider,
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + coroutinesDispatchers.main)
) {

    private val actionsRelay = MutableSharedFlow<Action>(extraBufferCapacity = 64)
    private val effectsRelay = MutableSharedFlow<SideEffect>(extraBufferCapacity = 64)

    fun wire(initialState: State, initialAction: Action? = null): Flow<State> {
        val effectHandlerEvents = effectToPartialState(
            effectsRelay
                .shareIn(coroutineScope, SharingStarted.Eagerly)
        )
            .flowOn(coroutinesDispatchers.io)

        val partialStateFlow = merge(
            effectHandlerEvents,
            actionsRelay
                .emitInitialAction(initialAction)
                .shareIn(coroutineScope, SharingStarted.WhileSubscribed())
                .onEach { listener?.onAction(it) }
                .map { action ->
                    stateReducer.partial(initialState, action)
                },
        )

        return partialStateFlow
            .onEach { listener?.onPartialState(it) }
            .scan(StateWithEffects<State, SideEffect>(initialState)) { vs, change ->
                stateReducer.reduce(vs.state, change)
            }
            .map { stateWithEffects ->
                stateWithEffects.effects.forEach { effect ->
                    listener?.onSideEffect(effect)
                    effectsRelay.emit(effect)
                }
                stateWithEffects.state
            }
            .distinctUntilChanged()
            .catch { listener?.onError(it) }
            .onEach { listener?.onState(it) }
            .flowOn(coroutinesDispatchers.computation)
            .stateIn(
                scope = coroutineScope,
                started = SharingStarted.Eagerly,
                initialValue = initialState
            )
    }

    private fun effectToPartialState(flow: Flow<SideEffect>): Flow<PartialState> {
        return effectHandler?.handle(flow) ?: emptyFlow()
    }

    fun processAction(intent: Action) {
        coroutineScope.launch { actionsRelay.emit(intent) }
    }

    interface Listener<in Action : Any, in State : Any, in SideEffect : Any, in PartialState : Any> {
        fun onAction(event: Action)

        fun onState(state: State)

        fun onPartialState(state: PartialState)

        fun onSideEffect(effect: SideEffect)

        fun onError(throwable: Throwable)
    }
}

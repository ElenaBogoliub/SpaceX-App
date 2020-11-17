package com.ebogoliub.flowmvi.data

import com.ebogoliub.flowmvi.EffectHandler
import com.ebogoliub.flowmvi.MviStore
import com.ebogoliub.flowmvi.StateReducer
import com.ebogoliub.flowmvi.StateWithEffects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

data class TestState(
    val counter: Int = 0
)

sealed class TestAction
object FirstAction : TestAction()
object SecondAction : TestAction()

sealed class TestEffect
object IncrementEffect : TestEffect()

sealed class TestPartialState
data class FirstPartialState(val value: Int) : TestPartialState()
object SecondPartialState : TestPartialState()

class TestStateReducer : StateReducer<TestAction, TestState, TestEffect, TestPartialState> {
    override fun reduce(
        state: TestState,
        partialState: TestPartialState
    ): StateWithEffects<TestState, TestEffect> {
        return when (partialState) {
            is FirstPartialState -> {
                StateWithEffects(
                    state.copy(counter = state.counter + partialState.value)
                )
            }
            is SecondPartialState -> StateWithEffects(state, listOf(IncrementEffect))
        }
    }

    override fun partial(state: TestState, event: TestAction): TestPartialState {
        return when (event) {
            FirstAction -> FirstPartialState(1)
            SecondAction -> SecondPartialState
        }
    }
}

class TestEffectHandler : EffectHandler<TestEffect, TestState, TestPartialState> {
    override fun handle(effects: Flow<TestEffect>): Flow<TestPartialState> {
        return effects
            .filterIsInstance<IncrementEffect>()
            .map { FirstPartialState(1) }
    }
}

class TestLogger : MviStore.Listener<TestAction, TestState, TestEffect, TestPartialState> {
    override fun onAction(event: TestAction) {
        println(event)
    }

    override fun onState(state: TestState) {
        println(state)
    }

    override fun onPartialState(state: TestPartialState) {
        println(state)
    }

    override fun onSideEffect(effect: TestEffect) {
        println(effect)
    }

    override fun onError(throwable: Throwable) {
        println(throwable)
    }
}
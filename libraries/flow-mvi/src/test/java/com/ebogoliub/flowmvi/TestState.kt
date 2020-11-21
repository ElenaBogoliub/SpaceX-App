package com.ebogoliub.flowmvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map

data class TestState(
    val counter: Int = 0
)

sealed class TestAction {
    object FirstAction : TestAction()
    object SecondAction : TestAction()
}

sealed class TestEffect {
    object IncrementEffect : TestEffect()
}

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
            is SecondPartialState -> StateWithEffects(state, listOf(TestEffect.IncrementEffect))
        }
    }

    override fun partial(state: TestState, event: TestAction): TestPartialState {
        return when (event) {
            TestAction.FirstAction -> FirstPartialState(1)
            TestAction.SecondAction -> SecondPartialState
        }
    }
}

class TestEffectHandler : EffectHandler<TestEffect, TestState, TestPartialState> {
    override fun handle(effects: Flow<TestEffect>): Flow<TestPartialState> {
        return effects
            .filterIsInstance<TestEffect.IncrementEffect>()
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
package com.ebogoliub.flowmvi

import app.cash.turbine.test
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class StoreTest {

    private val testScope = TestCoroutineScope()
    private lateinit var store: MviStore<TestState, TestAction, TestEffect, TestPartialState>

    @Before
    fun setUp() {
        store = MviStore(
            TestStateReducer(),
            TestEffectHandler(),
            TestLogger(),
            coroutinesDispatchers = CoroutinesDispatchers(),
            coroutineScope = testScope,
        )
    }

    @After
    fun cleanUp() {
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `Emits initial state to subscriber`() = runBlockingTest {
        store.wire(TestState(0)).test {
            assertEquals(TestState(0), expectItem())
        }
    }

    @Test
    fun `Passes dispatched FirstAction`() = runBlockingTest {
        testScope.launch {
            store.wire(TestState(0)).test {
                assertEquals(TestState(0), expectItem())
                assertEquals(TestState(1), expectItem())
            }
        }
        store.processAction(TestAction.FirstAction)
    }

    @Test
    fun `Passes dispatched SecondAction`() = runBlockingTest {
        testScope.launch {
            store.wire(TestState(0)).test {
                assertEquals(TestState(0), expectItem())
                assertEquals(TestState(1), expectItem())
            }
        }
        store.processAction(TestAction.SecondAction)
    }

    @Test
    fun `Passes dispatched all events`() = runBlockingTest {
        testScope.launch {
            store.wire(TestState(0)).test {
                assertEquals(TestState(0), expectItem())
                assertEquals(TestState(1), expectItem())
                assertEquals(TestState(2), expectItem())
            }
        }
        store.processAction(TestAction.FirstAction)
        store.processAction(TestAction.SecondAction)
    }
}
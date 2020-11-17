package com.ebogoliub.flowmvi

import app.cash.turbine.test
import com.ebogoliub.flowmvi.data.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@ObsoleteCoroutinesApi
class StoreTest {

    private val testScope = TestCoroutineScope()
    private lateinit var store: MviStore<TestState, TestAction, TestEffect, TestPartialState>

    @Before
    fun setUp() {
        store = MviStore(
            TestStateReducer(),
            TestEffectHandler(),
            TestLogger(),
            coroutinesDispatchers = CoroutinesDispatchersProvider(),
            coroutineScope = testScope,
        )
    }

    @After
    fun cleanUp() {
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `Emits initial state to subscriber`(): Unit = runBlockingTest {
        testScope.launch {
            store.wire(TestState(0)).test {
                assertEquals(TestState(0), expectItem())
            }
        }
    }

    @Test
    fun `Passes dispatched FirstAction`(): Unit = runBlockingTest {
        testScope.launch {
            store.wire(TestState(0)).test {
                assertEquals(TestState(0), expectItem())
                assertEquals(TestState(1), expectItem())
            }
        }
        store.processAction(FirstAction)
    }

    @Test
    fun `Passes dispatched SecondAction`(): Unit = runBlockingTest {
        testScope.launch {
            store.wire(TestState(0)).test {
                assertEquals(TestState(0), expectItem())
                assertEquals(TestState(1), expectItem())
            }
        }
        store.processAction(SecondAction)
    }

    @Test
    fun `Passes dispatched all events`(): Unit = runBlockingTest {
        testScope.launch {
            store.wire(TestState(0)).test {
                assertEquals(TestState(0), expectItem())
                assertEquals(TestState(1), expectItem())
                assertEquals(TestState(2), expectItem())
            }
        }
        store.processAction(FirstAction)
        store.processAction(SecondAction)
    }
}
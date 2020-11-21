package com.ebogoliub.flowmvi

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class CoroutinesDispatchers(
    override val main: CoroutineDispatcher = TestCoroutineDispatcher(),
    override val computation: CoroutineDispatcher = TestCoroutineDispatcher(),
    override val io: CoroutineDispatcher = TestCoroutineDispatcher()
) : CoroutinesDispatchersProvider
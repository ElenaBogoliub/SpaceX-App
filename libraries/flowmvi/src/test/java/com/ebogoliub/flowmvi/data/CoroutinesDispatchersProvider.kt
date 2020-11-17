package com.ebogoliub.flowmvi.data

import com.ebogoliub.flowmvi.CoroutinesDispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class CoroutinesDispatchersProvider(
    override val main: CoroutineDispatcher = TestCoroutineDispatcher(),
    override val computation: CoroutineDispatcher = TestCoroutineDispatcher(),
    override val io: CoroutineDispatcher = TestCoroutineDispatcher()
) : CoroutinesDispatchersProvider
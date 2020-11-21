package com.ebogoliub.flowmvi

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutinesDispatchersProvider {
    val main: CoroutineDispatcher
    val computation: CoroutineDispatcher
    val io: CoroutineDispatcher
}
package com.ebogoliub.flowmvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.emitInitialAction(initialAction: T?): Flow<T> {
    return initialAction?.let { onStart { emit(initialAction) } } ?: this
}
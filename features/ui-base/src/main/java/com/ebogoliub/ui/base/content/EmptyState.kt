package com.ebogoliub.ui.base.content

sealed class EmptyState
data class Message(val message: StringSource) : EmptyState()
data class MessageWithButton(
    val message: StringSource,
    val buttonText: StringSource
) : EmptyState()

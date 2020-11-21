package com.ebogoliub.ui.base.content

import androidx.annotation.StringRes
import com.ebogoliub.spacex.api.error.ApiException
import com.ebogoliub.spacex.api.error.NoInternetException
import com.ebogoliub.ui.base.R
import javax.inject.Inject

class ErrorMessageProvider @Inject constructor() {

    fun getErrorMessage(
        error: Throwable,
        @StringRes defaultMessageId: Int = R.string.common_message_failed
    ): StringSource = when (error) {
        is NoInternetException -> StringSource.Resources(R.string.common_message_no_internet_connection)
        is ApiException -> StringSource.Resources(R.string.common_message_load_failed)
        else -> StringSource.Resources(defaultMessageId)
    }

    fun getErrorState(
        error: Throwable,
        @StringRes defaultMessageId: Int = R.string.common_message_failed
    ): EmptyState = when (error) {
        is NoInternetException -> EmptyState.MessageWithButton(
            StringSource.Resources(R.string.common_message_no_internet_connection),
            StringSource.Resources(R.string.common_button_try_again)
        )
        is ApiException -> EmptyState.MessageWithButton(
            StringSource.Resources(R.string.common_message_load_failed),
            StringSource.Resources(R.string.common_button_try_again)
        )
        else -> EmptyState.MessageWithButton(
            StringSource.Resources(defaultMessageId),
            StringSource.Resources(R.string.common_button_try_again)
        )
    }
}
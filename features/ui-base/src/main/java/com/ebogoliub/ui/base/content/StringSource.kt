package com.ebogoliub.ui.base.content

import android.content.Context
import androidx.annotation.StringRes

sealed class StringSource {
    data class Resources(
        @StringRes val text: Int,
        val args: List<String> = emptyList()
    ) : StringSource()

    data class Text(val text: CharSequence) : StringSource()
}

fun StringSource.getText(context: Context): CharSequence {
    return when (this) {
        is StringSource.Resources -> context.getString(text, *args.toTypedArray())
        is StringSource.Text -> text
    }
}
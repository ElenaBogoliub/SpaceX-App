package com.ebogoliub.spacex.ui.extentions

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.os.ConfigurationCompat
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

@Suppress("DEPRECATION")
fun Activity.setStatusBarLightMode(inverseNightMode: Boolean = true) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val currentNightMode = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK)
        val currentFlags = window.decorView.systemUiVisibility
        val lightStatusBarFlag = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        if (inverseNightMode && currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            window.decorView.systemUiVisibility = currentFlags or lightStatusBarFlag
        } else {
            window.decorView.systemUiVisibility = currentFlags and lightStatusBarFlag.inv()
        }
    }
}

fun Context.formatDate(date: ZonedDateTime, pattern: String): String {
    val locale = ConfigurationCompat.getLocales(resources.configuration)[0]
    val formatter = DateTimeFormatter.ofPattern(pattern, locale)
    return date.format(formatter)
}


fun Any.bindColor(context: Context, @ColorRes id: Int) = lazy(LazyThreadSafetyMode.NONE) {
    ContextCompat.getColor(context, id)
}

fun Any.bindDrawable(context: Context, @DrawableRes id: Int) = lazy(LazyThreadSafetyMode.NONE) {
    ContextCompat.getDrawable(context, id)
}

fun Any.bindDimen(context: Context, @DimenRes id: Int) = lazy(LazyThreadSafetyMode.NONE) {
    context.resources.getDimension(id)
}

fun Any.bindString(context: Context, @StringRes id: Int) = lazy(LazyThreadSafetyMode.NONE) {
    context.getString(id)
}

fun Any.bindColor(view: View, @ColorRes id: Int) = lazy(LazyThreadSafetyMode.NONE) {
    ContextCompat.getColor(view.context, id)
}

fun Any.bindDimen(view: View, @DimenRes id: Int) = lazy(LazyThreadSafetyMode.NONE) {
    view.context.resources.getDimension(id)
}

fun Any.bindString(view: View, @StringRes id: Int) = lazy(LazyThreadSafetyMode.NONE) {
    view.context.getString(id)
}
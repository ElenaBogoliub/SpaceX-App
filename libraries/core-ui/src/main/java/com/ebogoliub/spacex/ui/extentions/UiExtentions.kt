package com.ebogoliub.spacex.ui.extentions

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
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


fun Context.getColorFromRes(@ColorRes id: Int): Int = ContextCompat.getColor(this, id)

fun Context.getDrawableFromRes(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)

fun Context.getDimenFromRes(@DimenRes id: Int) = resources.getDimension(id)

inline val Context.screenWidth: Int
    get() = Point().also {
        (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(
            it
        )
    }.x

inline val Int.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics
    ).toInt()
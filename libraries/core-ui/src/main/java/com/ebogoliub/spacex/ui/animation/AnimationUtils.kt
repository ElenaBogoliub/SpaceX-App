package com.ebogoliub.spacex.ui.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible

private const val DEFAULT_ANIMATION_DURATION = 200L

fun View.fadeInWithTranslationY(
    duration: Long = DEFAULT_ANIMATION_DURATION,
    translationY: Float = 55f,
    doOnEndCallback: (() -> Unit)? = null
) {
    alpha = 0f
    isVisible = true
    val animationSet = AnimatorSet()
    animationSet.playTogether(
        ObjectAnimator.ofFloat(this, View.ALPHA, 0f, 1f),
        ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, translationY, 0f)
    )
    animationSet.duration = duration
    animationSet.interpolator = DecelerateInterpolator()
    animationSet.doOnEnd { doOnEndCallback?.invoke() }
    animationSet.start()
}
package com.ebogoliub.spacex.ui.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible

private const val DEFAULT_ANIMATION_DURATION = 200L

fun View.fadeIn(duration: Long = DEFAULT_ANIMATION_DURATION) {
    alpha = 0f
    isVisible = true
    this.animate()
        .alpha(1f)
        .setDuration(duration)
        .start()
}

fun View.fadeInWithTranslationY(duration: Long = DEFAULT_ANIMATION_DURATION, doOnEndCallback: (() -> Unit)? = null, translationY: Float = 55f) {
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
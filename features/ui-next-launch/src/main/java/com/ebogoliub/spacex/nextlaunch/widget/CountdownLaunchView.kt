package com.ebogoliub.spacex.nextlaunch.widget

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.ebogoliub.spacex.data.entities.DatePrecision
import com.ebogoliub.spacex.data.entities.Launch
import com.ebogoliub.spacex.features.ui.next_launch.R
import org.threeten.bp.Duration
import org.threeten.bp.ZonedDateTime
import java.util.concurrent.TimeUnit

class CountdownLaunchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var timer: CountDownTimer? = null

    fun setupCountdown(launch: Launch) = with(launch) {
        timer?.cancel()
        when (tentativeMaxPrecision) {
            DatePrecision.hour -> {
                timer = createTimer(
                    launchTime = Duration.between(ZonedDateTime.now(), launchDateUtc).toMillis(),
                    onTick = { timeToFinish ->
                        post { text = createCountdownText(timeToFinish) }
                    },
                    onCountDownFinish = {
                        post {
                            text = if (launchSuccess == true) {
                                context.getString(R.string.common_launch_successful)
                            } else {
                                context.getString(R.string.common_launch_finish)
                            }
                        }
                    }
                )
            }
            DatePrecision.day -> {
                val days = Duration.between(ZonedDateTime.now(), launchDateUtc).toDays()
                val plural = resources.getQuantityString(
                    R.plurals.next_launch_day,
                    days.toInt(),
                    days.toInt()
                )
                text = plural
            }
            else -> {
                text = context.getString(R.string.next_launch_countdown_uncertain)
            }
        }
    }

    private fun createCountdownText(
        timeUntilFinished: Long
    ): String {
        val timeUnit = TimeUnit.MILLISECONDS
        var delta = timeUntilFinished
        val days = timeUnit.toDays(delta)
        if (days > 0) {
            delta %= days * timeUnit.convert(1, TimeUnit.DAYS)
        }
        val hours = timeUnit.toHours(delta)
        if (hours > 0) {
            delta %= hours * timeUnit.convert(1, TimeUnit.HOURS)
        }
        val minutes = timeUnit.toMinutes(delta)
        if (minutes > 0) {
            delta %= minutes * timeUnit.convert(1, TimeUnit.MINUTES)
        }
        val seconds = timeUnit.toSeconds(delta)

        return "${days}D:${hours}H:${minutes}M:${seconds}S"
    }

    private fun createTimer(
        launchTime: Long,
        onTick: (Long) -> Unit,
        onCountDownFinish: () -> Unit
    ): CountDownTimer {
        return object : CountDownTimer(launchTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                onTick(millisUntilFinished)
            }

            override fun onFinish() {
                onCountDownFinish()
            }
        }.start()
    }

    override fun onDetachedFromWindow() {
        cancelTimer()
        super.onDetachedFromWindow()
    }

    private fun cancelTimer() {
        timer?.cancel()
        timer = null
    }
}

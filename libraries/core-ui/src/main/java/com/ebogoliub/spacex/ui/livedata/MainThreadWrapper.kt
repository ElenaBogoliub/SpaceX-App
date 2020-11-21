package com.ebogoliub.spacex.ui.livedata

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor

@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
object MainThreadWrapper {
    @Suppress("ObjectPropertyName")
    private var _executor: Executor? = null

    /**
     * The executor used for running code on the main thread.
     */
    var executor: Executor
        get() {
            if (_executor == null) {
                _executor = MainThreadExecutor()
            }
            return _executor!!
        }
        set(value) {
            _executor = value
        }

    /**
     * Reset the internal executor to the real Android main thread.
     */
    fun resetExecutor() {
        _executor = null
    }

    private class MainThreadExecutor : Executor {
        private val handler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            handler.post { command.run() }
        }
    }
}

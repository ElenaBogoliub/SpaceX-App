package com.ebogoliub.spacex.ui.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import java.util.*

/**
 * A [MutableLiveData] implementation that queues all values that are set or posted
 * to it while its observer is inactive. When its observer becomes active,
 * it is notified about any queued values. The order of the events is preserved.
 */
internal class QueuedSingleEventLiveData<T : Any> : SingleEventLiveData<T>() {

    private val queue: Queue<T> = LinkedList()

    override fun postValue(value: T?) {
        MainThreadWrapper.executor.execute { setValue(value) }
    }

    @MainThread
    override fun setValue(t: T?) {
        if (hasActiveObservers() && queue.isEmpty()) {
            super.setValue(t)
        } else {
            queue.add(t)
        }
    }

    override fun onActive() {
        var element = queue.poll()
        while (element != null) {
            super.setValue(element)
            element = queue.poll()
        }
    }

}
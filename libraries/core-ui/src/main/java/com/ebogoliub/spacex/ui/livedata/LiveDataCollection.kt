package com.ebogoliub.spacex.ui.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

interface LiveDataCollection<T : Any> {

    fun observe(owner: LifecycleOwner, observer: Observer<T>)

}

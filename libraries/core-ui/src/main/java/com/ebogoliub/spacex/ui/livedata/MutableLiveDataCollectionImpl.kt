package com.ebogoliub.spacex.ui.livedata

import androidx.lifecycle.*

/**
 * An implementation of [MutableLiveDataCollection] which can use
 * any [MutableLiveData] implementation internally for its contained
 * [LiveData] instances.
 *
 * @property factory the factory method used to create a new [MutableLiveData]
 *                   if required by a new observer being added.
 */
internal class MutableLiveDataCollectionImpl<T : Any>(
    private val factory: () -> MutableLiveData<T>
) : MutableLiveDataCollection<T> {

    internal val activeLiveData: MutableSet<MutableLiveData<T>> = mutableSetOf()

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        val liveData = factory()
        activeLiveData += liveData
        attachRemover(owner, liveData)
        liveData.observe(owner, observer)
    }

    override fun setValue(value: T?) {
        activeLiveData.forEach { it.setValue(value) }
    }

    override fun postValue(value: T?) {
        activeLiveData.forEach { it.postValue(value) }
    }

    private fun removeLiveData(liveData: LiveData<T>) {
        activeLiveData.remove(liveData)
    }

    private fun attachRemover(owner: LifecycleOwner, liveData: MutableLiveData<T>) {
        val watcher = LiveDataRemover(liveData)
        owner.lifecycle.addObserver(watcher)
    }

    inner class LiveDataRemover(private val liveData: MutableLiveData<T>) :
        LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            removeLiveData(liveData)
        }
    }

}
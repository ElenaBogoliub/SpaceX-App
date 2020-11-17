package com.ebogoliub.spacex.ui.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ebogoliub.flowmvi.CoroutinesDispatchersProvider
import com.ebogoliub.flowmvi.EffectHandler
import com.ebogoliub.flowmvi.MviStore
import com.ebogoliub.flowmvi.StateReducer
import com.ebogoliub.spacex.core.di.CoroutinesDispatchers
import com.ebogoliub.spacex.ui.livedata.*
import com.ebogoliub.spacex.ui.livedata.MutableLiveDataCollection
import com.ebogoliub.spacex.ui.livedata.MutableLiveDataCollectionImpl
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber

abstract class BaseMviViewModel<State : Any, Action : Any, SideEffect : Any, PartialState : Any>(
    initialState: State,
    initialAction: Action,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    protected val logTag: String by lazy(mode = LazyThreadSafetyMode.NONE) { "BaseViewModel ($this)" }

    val state: LiveData<State>

    protected val viewEvents: MutableLiveDataCollection<SingleLiveEvent> =
        MutableLiveDataCollectionImpl(::ActiveOnlySingleEventLiveData)
    val events: LiveDataCollection<SingleLiveEvent> = viewEvents

    protected val queuedViewEvents: MutableLiveDataCollection<QueuedSingleLiveEvent> =
        MutableLiveDataCollectionImpl(::QueuedSingleEventLiveData)
    val queuedEvents: LiveDataCollection<QueuedSingleLiveEvent> = queuedViewEvents

    private val store = initStore()

    init {
        state = store.wire(initialState, initialAction).asLiveData()
    }

    private fun initStore(): MviStore<State, Action, SideEffect, PartialState> {
        return MviStore(
            stateReducer = getStateReducer(),
            effectHandler = getEffectHandler(),
            listener = getListener(),
            coroutinesDispatchers = coroutinesDispatchers.toMviStoreDispatchers(),
            coroutineScope = viewModelScope
        )
    }

    protected fun getListener(): MviStore.Listener<Action, State, SideEffect, PartialState>? {
        return object : MviStore.Listener<Action, State, SideEffect, PartialState> {
            override fun onAction(event: Action) {
                Timber.tag(logTag).d("Action: %s", event)
            }

            override fun onState(state: State) {
                Timber.tag(logTag).d("State: %s", state)
            }

            override fun onPartialState(state: PartialState) {
                Timber.tag(logTag).d("PartialState: %s", state)
            }

            override fun onSideEffect(effect: SideEffect) {
                Timber.tag(logTag).d("SideEffect: %s", effect)
            }

            override fun onError(throwable: Throwable) {
                Timber.tag(logTag).e(throwable)
            }
        }
    }


    protected abstract fun getStateReducer(): StateReducer<Action, State, SideEffect, PartialState>
    protected open fun getEffectHandler(): EffectHandler<SideEffect, State, PartialState>? = null
    protected open fun sendInitAction() = Unit

    protected fun postEvent(event: SingleLiveEvent) {
        viewEvents.postValue(event)
    }

    protected fun postQueuedEvent(event: QueuedSingleLiveEvent) {
        queuedViewEvents.postValue(event)
    }

    fun processAction(action: Action) {
        store.processAction(action)
    }

    private fun CoroutinesDispatchers.toMviStoreDispatchers(): CoroutinesDispatchersProvider {
        return object : CoroutinesDispatchersProvider {
            override val main = this@toMviStoreDispatchers.main
            override val computation = this@toMviStoreDispatchers.computation
            override val io = this@toMviStoreDispatchers.io
        }
    }
}
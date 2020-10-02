package com.example.projectx.utlities.onetimeevent

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.example.projectx.data.Resource
import com.floraonline.floranational.utlities.onetimeevent.OneTimeEvent

/**
 * OnEventUnhandled:Block to be executed if the event has not been handled by any observer
 */
fun <T> LiveData<OneTimeEvent<T>>.observeEvent(
    owner: LifecycleOwner,
    onEventUnhandled: ((T) -> Unit)?,
    onEventHandled: (((T) -> Unit))? = null
): Observer<OneTimeEvent<T>> {
    val observer = Observer<OneTimeEvent<T>> {
        if (onEventUnhandled != null) {
            it?.getValue()?.let(onEventUnhandled)
        }
        if (onEventHandled != null) {
            it?.peekData()?.let(onEventHandled)
        }
    }
    observe(owner, observer)
    return observer
}


fun <A, B> LiveData<Resource<A>>.combineAndCompute(other: LiveData<Resource<B>>): MediatorLiveData<Resource<Boolean>> {

    val result = MediatorLiveData<Resource<Boolean>>()

    val mergeF = {
        val source1Value = this.value
        val source2Value = other.value
            if (source1Value!=null&&source2Value!=null&&source1Value !is Resource.LOADING && source2Value !is Resource.LOADING) {
                result.value = Resource.SUCCESS(true)
            }else{
                result.value = Resource.LOADING()
            }
    }

    result.addSource(this) { mergeF.invoke() }
    result.addSource(other) { mergeF.invoke() }

    return result
}
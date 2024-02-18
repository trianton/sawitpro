package id.naupal.utils.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

fun <T> LifecycleOwner.observeEvent(liveData: LiveData<Event<T>>, action: (t: T) -> Unit) {
    liveData.observe(this, EventObserver {
        action.invoke(it)
    })
}
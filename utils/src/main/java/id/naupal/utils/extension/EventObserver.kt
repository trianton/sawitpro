package id.naupal.utils.extension

import androidx.lifecycle.Observer

/**
 * Created by Naupal T. on 03/04/21.
 */

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}
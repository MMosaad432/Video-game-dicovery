package com.thirdwayv.videogamediscovery.core.utils

import androidx.lifecycle.Observer
import com.thirdwayv.videogamediscovery.core.others.Resource

class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (!hasBeenHandled) {
            hasBeenHandled = true
            content
        } else null
    }

    fun peekContent() = content
}

class EventObserver<T>(
    private inline val onError: ((String) -> Unit)? = null,
    private inline val onSuccess: (T) -> Unit
) : Observer<Event<Resource<T>>> {
    override fun onChanged(t: Event<Resource<T>>?) {
        when (val content = t?.peekContent()) {
            is Resource.Success -> {
                content.data?.let(onSuccess)
            }
            is Resource.Failure -> {
                t.getContentIfNotHandled()?.let {
                    onError?.let { error ->
                        error(content.message ?: "")
                    }
                }
            }
        }
    }
}
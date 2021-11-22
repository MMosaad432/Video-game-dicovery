package com.thirdwayv.videogamediscovery.core.extentions

import androidx.lifecycle.LiveData
import com.thirdwayv.videogamediscovery.core.others.LiveDataToOnTimeObserve

fun <T> LiveData<T>.toOneTimeObserve(): LiveData<T> {
    val result =
        LiveDataToOnTimeObserve<T>()
    result.addSource(this) {
        result.value = it
    }
    return result
}
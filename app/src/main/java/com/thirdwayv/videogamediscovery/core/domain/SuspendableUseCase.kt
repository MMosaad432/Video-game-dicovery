package com.thirdwayv.videogamediscovery.core.domain

import com.thirdwayv.videogamediscovery.core.others.CoroutineContextProvider
import com.thirdwayv.videogamediscovery.core.others.Resource
import com.thirdwayv.videogamediscovery.core.utils.RetrofitCoroutines
import com.thirdwayv.videogamediscovery.core.views.viewmodel.ITriggerViews

abstract class SuspendableUseCase<I, O>(
    private val dispatcher: CoroutineContextProvider
) {
    abstract suspend fun execute(input: I? = null, iTriggerViews: ITriggerViews? = null): O

    protected suspend fun <T> networkCall(
        iTriggerViews: ITriggerViews? = null,
        block: suspend () -> T
    ): Resource<T> {
        return RetrofitCoroutines(
            iTriggerViews
        ).networkCall(
            block,
            dispatcher = dispatcher
        )
    }

}
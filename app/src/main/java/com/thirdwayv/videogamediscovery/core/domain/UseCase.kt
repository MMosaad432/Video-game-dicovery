package com.thirdwayv.videogamediscovery.core.domain

import com.thirdwayv.videogamediscovery.core.views.viewmodel.ITriggerViews

abstract class UseCase<I, O>(iTriggerViews: ITriggerViews) {
    abstract fun execute(input: I? = null): O
}
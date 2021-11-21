package com.thirdwayv.videogamediscovery.features.main_activity.presentation.viewmodel

import com.thirdwayv.videogamediscovery.core.views.viewmodel.Result

sealed class MainResult : Result {
    data class SuccessFetch(val isFavoriteSelected: Boolean):MainResult()
}
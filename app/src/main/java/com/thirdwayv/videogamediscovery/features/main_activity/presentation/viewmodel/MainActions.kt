package com.thirdwayv.videogamediscovery.features.main_activity.presentation.viewmodel

import com.thirdwayv.videogamediscovery.core.views.viewmodel.Action

sealed class MainActions : Action {
    object GetIsFavoriteSelected : MainActions()
}
package com.thirdwayv.videogamediscovery.features.main_activity.presentation.viewmodel

import com.thirdwayv.videogamediscovery.core.views.viewmodel.ViewState

sealed class MainViewState : ViewState {
    data class SuccessFetch(val isFavoriteSelected: Boolean) : MainViewState()
}
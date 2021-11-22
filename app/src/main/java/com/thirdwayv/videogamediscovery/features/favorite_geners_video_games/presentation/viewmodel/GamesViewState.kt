package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.viewmodel

import com.thirdwayv.videogamediscovery.core.views.viewmodel.ViewState
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.GamesResponse

sealed class GamesViewState : ViewState {
    data class SuccessFetch(val games: GamesResponse) : GamesViewState()
    object FailureFetch : GamesViewState()
}
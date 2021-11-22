package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.viewmodel

import com.thirdwayv.videogamediscovery.core.views.viewmodel.Result
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.GamesResponse

sealed class GamesResult : Result {
    data class SuccessFetch(val games: GamesResponse) : GamesResult()
    object FailureFetch : GamesResult()
}
package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.viewmodel

import com.thirdwayv.videogamediscovery.core.views.viewmodel.Action
import com.thirdwayv.videogamediscovery.features.main_activity.presentation.viewmodel.MainActions

sealed class GamesActions : Action {
    object GetGames : GamesActions()
}
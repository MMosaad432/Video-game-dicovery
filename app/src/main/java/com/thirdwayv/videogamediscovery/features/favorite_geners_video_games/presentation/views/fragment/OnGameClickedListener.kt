package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.views.fragment

import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.Game

interface OnGameClickedListener {
    fun onGameClicked(game: Game)
}

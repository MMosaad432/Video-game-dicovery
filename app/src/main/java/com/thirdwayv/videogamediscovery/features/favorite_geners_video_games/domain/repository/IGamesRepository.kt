package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.domain.repository

import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.GamesResponse
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.GamesRequest

interface IGamesRepository {
    suspend fun getGames(
        request: GamesRequest
    ): GamesResponse

    suspend fun getFavoriteGenreListFromSharedPref(): MutableList<Int>
}
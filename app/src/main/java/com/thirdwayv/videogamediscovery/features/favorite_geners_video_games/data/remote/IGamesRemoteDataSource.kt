package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.remote

import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.GamesResponse
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.GamesRequest

interface IGamesRemoteDataSource {
    suspend fun getGames(
        gamesRequest: GamesRequest
    ): GamesResponse
}
package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.remote

import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.GamesResponse
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.GamesRequest
import com.thirdwayv.videogamediscovery.features.utils.API_KEY
import javax.inject.Inject

class IGamesRemoteDataSourceImpl @Inject constructor(
    private val apiService: GamesApiService,
) : IGamesRemoteDataSource {
    override suspend fun getGames(
        gamesRequest: GamesRequest
    ): GamesResponse {
        return apiService.getGames(
            API_KEY,
            gamesRequest.favoriteGenresIds,
            gamesRequest.page,
            gamesRequest.pageSize
        )
    }
}
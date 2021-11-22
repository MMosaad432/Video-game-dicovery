package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.remote

import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.GamesResponse
import com.thirdwayv.videogamediscovery.features.utils.GAMES_CLOUD
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApiService {
    @GET(GAMES_CLOUD)
    suspend fun getGames(
        @Query("key") key: String,
        @Query("genres") favoriteGenresIds: List<Int>?,
        @Query("page") page: Int?,
        @Query("page_size") pageSize: Int?,
    ): GamesResponse
}
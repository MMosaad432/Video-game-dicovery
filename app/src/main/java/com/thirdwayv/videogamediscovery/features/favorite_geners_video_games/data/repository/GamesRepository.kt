package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.repository

import com.thirdwayv.videogamediscovery.core.others.pref.ISharedPreferenceManager
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.GamesRequest
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.GamesResponse
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.remote.IGamesRemoteDataSource
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.domain.repository.IGamesRepository
import com.thirdwayv.videogamediscovery.features.utils.FAVORITE_GENRES_IDS
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val iRemoteDataSource: IGamesRemoteDataSource,
    private val iLocalDataSource: ISharedPreferenceManager
) : IGamesRepository {
    override suspend fun getGames(request: GamesRequest): GamesResponse {
        return iRemoteDataSource.getGames(request)
    }

    override suspend fun getFavoriteGenreListFromSharedPref(): MutableList<Int> {
        return iLocalDataSource.getList(FAVORITE_GENRES_IDS)
    }
}
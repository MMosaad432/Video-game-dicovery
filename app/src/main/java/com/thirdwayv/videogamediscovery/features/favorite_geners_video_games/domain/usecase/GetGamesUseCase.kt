package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.domain.usecase

import com.thirdwayv.videogamediscovery.core.domain.SuspendableUseCase
import com.thirdwayv.videogamediscovery.core.others.CoroutineContextProvider
import com.thirdwayv.videogamediscovery.core.others.Resource
import com.thirdwayv.videogamediscovery.core.views.viewmodel.ITriggerViews
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.GamesRequest
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.domain.repository.IGamesRepository
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.viewmodel.GamesResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetGamesUseCase @Inject constructor(
    private val iGamesRepository: IGamesRepository, dispatcher: CoroutineContextProvider
) : SuspendableUseCase<GamesRequest, GamesResult>(dispatcher) {
    override suspend fun execute(
        input: GamesRequest?,
        iTriggerViews: ITriggerViews?
    ): GamesResult {
        val gamesRequest = input ?: GamesRequest()
        val favoriteGenresIds = iGamesRepository.getFavoriteGenreListFromSharedPref()
        gamesRequest.favoriteGenresIds = favoriteGenresIds
        return when (val response = networkCall { iGamesRepository.getGames(gamesRequest) }) {
            is Resource.Success -> {
                GamesResult.SuccessFetch(response.data)
            }
            is Resource.Failure -> {
                GamesResult.FailureFetch
            }
        }
    }

}
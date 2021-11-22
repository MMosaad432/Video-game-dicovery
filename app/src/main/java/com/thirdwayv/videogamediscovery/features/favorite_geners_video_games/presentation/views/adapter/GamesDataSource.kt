package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.views.adapter

import com.thirdwayv.videogamediscovery.core.views.adapters.BaseDataSource
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.Game
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.GamesRequest
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.viewmodel.GamesResult
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.viewmodel.GamesViewModel

class GamesDataSource(
    private val mViewModel: GamesViewModel,
) : BaseDataSource<Game>(mViewModel) {

    var totalCount: Int? = null
    override suspend fun fetchData(pageNumber: Int, pageSize: Int): List<Game> {
        if (totalCount != null && mViewModel.gamesLiveData?.value?.size ?: 0 >= totalCount ?: 0)
            return listOf()

        val gamesRequest = GamesRequest()
        gamesRequest.page = pageNumber
        val result = mViewModel.getGamesUseCase.execute(gamesRequest, mViewModel.iTriggerViews)
        return if (result is GamesResult.SuccessFetch) {
            totalCount = result.games.count
            result.games.results
        } else listOf()
    }
}
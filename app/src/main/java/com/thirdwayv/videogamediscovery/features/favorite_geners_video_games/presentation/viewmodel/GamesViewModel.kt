package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.viewmodel

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.domain.usecase.GetGamesUseCase
import com.thirdwayv.videogamediscovery.core.others.CoroutineContextProvider
import com.thirdwayv.videogamediscovery.core.views.adapters.BaseDataFactory
import com.thirdwayv.videogamediscovery.core.views.adapters.BaseDataSource
import com.thirdwayv.videogamediscovery.core.views.viewmodel.BaseViewModel
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.Game
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.views.adapter.GamesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    val getGamesUseCase: GetGamesUseCase,
    dispatcher: CoroutineContextProvider
) : BaseViewModel<GamesViewState, GamesActions, GamesResult>(dispatcher) {

    var gamesLiveData: LiveData<PagedList<Game>>? = null
    private var dataFactory = MutableLiveData<BaseDataFactory<Game>>()
    lateinit var gamesDataSource: GamesDataSource

    fun handlePaging(){
        fetchGames()
        val pagedListConfig = PagedList.Config.Builder()
            .setInitialLoadSizeHint(20)
            .setPageSize(2).build()
        gamesLiveData= Transformations.switchMap(dataFactory, Function {
            return@Function LivePagedListBuilder(it, pagedListConfig).build()
        })
    }
    private fun fetchGames() {
        dataFactory.value = object : BaseDataFactory<Game>(this) {
            override fun onCreateDataSource(baseViewModel: BaseViewModel<*,*,*>): BaseDataSource<Game> {
                gamesDataSource = GamesDataSource(this@GamesViewModel)
                return gamesDataSource
            }
        }
    }
    override fun handle(action: GamesActions) = liveData {
        when (action) {
            GamesActions.GetGames -> emit(getGamesUseCase.execute())
        }
    }

    override fun reduce(result: GamesResult): GamesViewState = when (result) {
        is GamesResult.SuccessFetch -> GamesViewState.SuccessFetch(result.games)
        GamesResult.FailureFetch -> GamesViewState.FailureFetch
    }

}
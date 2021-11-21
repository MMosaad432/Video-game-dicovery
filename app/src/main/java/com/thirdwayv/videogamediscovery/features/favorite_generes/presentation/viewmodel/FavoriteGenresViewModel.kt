package com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.viewmodel

import androidx.lifecycle.liveData
import com.thirdwayv.videogamediscovery.core.others.CoroutineContextProvider
import com.thirdwayv.videogamediscovery.core.views.viewmodel.BaseViewModel
import com.thirdwayv.videogamediscovery.features.favorite_generes.domain.usecase.GetGenresUseCase
import com.thirdwayv.videogamediscovery.features.favorite_generes.domain.usecase.SaveFavoriteGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteGenresViewModel @Inject constructor(
    private val genresUseCase: GetGenresUseCase,
    private val saveFavoriteGenresUseCase: SaveFavoriteGenresUseCase,
    dispatcher: CoroutineContextProvider
) : BaseViewModel<FavoriteGenresViewState, FavoriteGenresActions, FavoriteGenreResult>(dispatcher) {

    override var internalViewState: FavoriteGenresViewState = FavoriteGenresViewState()

    override fun handle(action: FavoriteGenresActions) = liveData {
        when (action) {
            FavoriteGenresActions.GetGenres -> emit(genresUseCase.execute(iTriggerViews = iTriggerViews))
            FavoriteGenresActions.SaveUserFavoriteGenres -> emit(
                saveFavoriteGenresUseCase.execute(
                    internalViewState.favoriteGenresIds,
                    iTriggerViews = iTriggerViews
                )
            )
        }
    }

    override fun reduce(result: FavoriteGenreResult): FavoriteGenresViewState {
        when (result) {
            FavoriteGenreResult.FailGenreFetch -> {
                internalViewState.genres = mutableListOf()
            }
            is FavoriteGenreResult.SuccessGenreFetch -> {
                internalViewState.genres = result.genres
                internalViewState.favoriteGenresIds = result.favoriteGenresIds
            }
            FavoriteGenreResult.SuccessFavoriteGenreSave -> {
                internalViewState.successFavoriteGenreSave =
                    internalViewState.favoriteGenresIds.size != 0
            }
        }
        return internalViewState
    }
}
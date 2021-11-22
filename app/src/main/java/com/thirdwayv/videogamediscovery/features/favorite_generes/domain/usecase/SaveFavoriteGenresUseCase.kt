package com.thirdwayv.videogamediscovery.features.favorite_generes.domain.usecase

import com.thirdwayv.videogamediscovery.core.domain.SuspendableUseCase
import com.thirdwayv.videogamediscovery.core.others.CoroutineContextProvider
import com.thirdwayv.videogamediscovery.core.views.viewmodel.ITriggerViews
import com.thirdwayv.videogamediscovery.features.favorite_generes.domain.repository.IGenresRepository
import com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.viewmodel.FavoriteGenreResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SaveFavoriteGenresUseCase @Inject constructor(
    private val iGenresRepository: IGenresRepository,
    dispatcher: CoroutineContextProvider
) : SuspendableUseCase<MutableList<Int>, FavoriteGenreResult>(
    dispatcher
) {
    override suspend fun execute(
        input: MutableList<Int>?,
        iTriggerViews: ITriggerViews?
    ): FavoriteGenreResult {
        iGenresRepository.setFavoriteSelected(input != null && input.size != 0)
        iGenresRepository.saveFavoriteGenreListFromSharedPref(input ?: mutableListOf())
        return FavoriteGenreResult.SuccessFavoriteGenreSave
    }

}
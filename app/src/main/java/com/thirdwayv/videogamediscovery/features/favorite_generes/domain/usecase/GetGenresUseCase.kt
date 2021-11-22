package com.thirdwayv.videogamediscovery.features.favorite_generes.domain.usecase

import com.thirdwayv.videogamediscovery.features.favorite_generes.domain.repository.IGenresRepository
import com.thirdwayv.videogamediscovery.core.domain.SuspendableUseCase
import com.thirdwayv.videogamediscovery.core.others.CoroutineContextProvider
import com.thirdwayv.videogamediscovery.core.others.Resource
import com.thirdwayv.videogamediscovery.core.views.viewmodel.ITriggerViews
import com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.viewmodel.FavoriteGenreResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetGenresUseCase @Inject constructor(
    private val iGenresRepository: IGenresRepository,
    dispatcher: CoroutineContextProvider
) : SuspendableUseCase<Unit, FavoriteGenreResult>(dispatcher) {
    override suspend fun execute(input: Unit?, iTriggerViews: ITriggerViews?): FavoriteGenreResult {
        return when (val response = networkCall(iTriggerViews) { iGenresRepository.getGenres() }) {
            is Resource.Success -> {
                val favoriteList = iGenresRepository.getFavoriteGenreListFromSharedPref()
                val genres = response.data.genres
                favoriteList.forEach {
                    genres.find { genre -> genre.id == it }?.isFavorite = true
                }
                FavoriteGenreResult.SuccessGenreFetch(genres, favoriteList)
            }
            is Resource.Failure -> {
                FavoriteGenreResult.FailGenreFetch
            }
        }

    }
}
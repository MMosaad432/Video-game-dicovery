package com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.viewmodel

import com.thirdwayv.videogamediscovery.core.views.viewmodel.Result
import com.thirdwayv.videogamediscovery.features.favorite_generes.data.model.Genre

sealed class FavoriteGenreResult : Result {
    data class SuccessGenreFetch(val genres: List<Genre>, val favoriteGenresIds: MutableList<Int>) :
        FavoriteGenreResult()

    object FailGenreFetch : FavoriteGenreResult()
    object SuccessFavoriteGenreSave : FavoriteGenreResult()
}
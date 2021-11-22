package com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.viewmodel

import com.thirdwayv.videogamediscovery.core.views.viewmodel.ViewState
import com.thirdwayv.videogamediscovery.features.favorite_generes.data.model.Genre

data class FavoriteGenresViewState(
    var genres: List<Genre> = listOf(),
    var successFavoriteGenreSave: Boolean = false,
    var favoriteGenresIds: MutableList<Int> = mutableListOf()
) : ViewState
package com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.viewmodel

import com.thirdwayv.videogamediscovery.core.views.viewmodel.Action
import com.thirdwayv.videogamediscovery.features.favorite_generes.data.model.Genre

sealed class FavoriteGenresActions : Action {
    object GetGenres : FavoriteGenresActions()
    object GetFavoriteGenresLocal : FavoriteGenresActions()
    object SaveUserFavoriteGenres : FavoriteGenresActions()
}
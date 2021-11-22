package com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.view.fragment

import com.thirdwayv.videogamediscovery.features.favorite_generes.data.model.Genre

interface OnFavoriteGenreClicked {
    fun onAddFavoriteGenreClicked(genreId: Int)
    fun onRemoveFavoriteGenreClicked(genreId: Int)
}
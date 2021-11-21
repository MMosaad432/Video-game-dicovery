package com.thirdwayv.videogamediscovery.features.favorite_generes.domain.repository

import com.thirdwayv.videogamediscovery.features.favorite_generes.data.model.Genres

interface IGenresRepository {
    suspend fun getGenres(): Genres
    suspend fun saveFavoriteGenreListFromSharedPref(input: MutableList<Int>)
    suspend fun getFavoriteGenreListFromSharedPref(): MutableList<Int>
    suspend fun setFavoriteSelected(isFavoriteSelected: Boolean)
}
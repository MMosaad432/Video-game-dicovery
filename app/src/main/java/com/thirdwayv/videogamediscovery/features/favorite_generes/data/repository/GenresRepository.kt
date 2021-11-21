package com.thirdwayv.videogamediscovery.features.favorite_generes.data.repository

import com.thirdwayv.videogamediscovery.core.others.pref.ISharedPreferenceManager
import com.thirdwayv.videogamediscovery.features.favorite_generes.data.model.Genre
import com.thirdwayv.videogamediscovery.features.favorite_generes.data.model.Genres
import com.thirdwayv.videogamediscovery.features.favorite_generes.data.remote.IGenresRemoteDataSource
import com.thirdwayv.videogamediscovery.features.favorite_generes.domain.repository.IGenresRepository
import com.thirdwayv.videogamediscovery.features.utils.FAVORITE_GENRES_IDS
import com.thirdwayv.videogamediscovery.features.utils.FAVORITE_SELECTED
import javax.inject.Inject

class GenresRepository @Inject constructor(
    private val iRemoteDataSource: IGenresRemoteDataSource,
    private val iLocalDataSource: ISharedPreferenceManager
) : IGenresRepository {
    override suspend fun getGenres(): Genres {
        return iRemoteDataSource.getGenres()
    }

    override suspend fun saveFavoriteGenreListFromSharedPref(input: MutableList<Int>) {
        iLocalDataSource.saveList(FAVORITE_GENRES_IDS, input)
    }

    override suspend fun getFavoriteGenreListFromSharedPref(): MutableList<Int> {
        return iLocalDataSource.getList(FAVORITE_GENRES_IDS)
    }

    override suspend fun setFavoriteSelected(isFavoriteSelected: Boolean) {
        iLocalDataSource.saveBoolean(FAVORITE_SELECTED, isFavoriteSelected)
    }
}
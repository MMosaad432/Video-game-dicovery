package com.thirdwayv.videogamediscovery.features.favorite_generes.data.remote

import com.thirdwayv.videogamediscovery.features.favorite_generes.data.model.Genres
import com.thirdwayv.videogamediscovery.features.utils.API_KEY
import javax.inject.Inject

class GenresRemoteDataSource @Inject constructor(
    private val genresService: GenresService
) : IGenresRemoteDataSource {
    override suspend fun getGenres(): Genres {
        return genresService.getGenres(API_KEY)
    }
}
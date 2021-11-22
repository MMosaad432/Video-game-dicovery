package com.thirdwayv.videogamediscovery.features.favorite_generes.data.remote

import com.thirdwayv.videogamediscovery.features.favorite_generes.data.model.Genres

interface IGenresRemoteDataSource {
    suspend fun getGenres(): Genres
}
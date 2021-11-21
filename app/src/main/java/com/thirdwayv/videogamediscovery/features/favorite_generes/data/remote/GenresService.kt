package com.thirdwayv.videogamediscovery.features.favorite_generes.data.remote

import com.thirdwayv.videogamediscovery.features.favorite_generes.data.model.Genres
import com.thirdwayv.videogamediscovery.features.utils.GENRES_CLOUD
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresService {
    @GET(GENRES_CLOUD)
    suspend fun getGenres(@Query("key") key: String): Genres
}
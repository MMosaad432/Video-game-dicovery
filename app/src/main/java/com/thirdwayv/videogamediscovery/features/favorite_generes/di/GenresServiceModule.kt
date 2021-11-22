package com.thirdwayv.videogamediscovery.features.favorite_generes.di

import com.thirdwayv.videogamediscovery.features.favorite_generes.data.remote.GenresService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
object GenresServiceModule {
    @Provides
    fun getGenresService(retrofit: Retrofit): GenresService {
        return retrofit.create(GenresService::class.java)
    }
}
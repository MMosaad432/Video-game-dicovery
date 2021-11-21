package com.thirdwayv.videogamediscovery.features.favorite_generes.di

import com.thirdwayv.videogamediscovery.features.favorite_generes.data.remote.IGenresRemoteDataSource
import com.thirdwayv.videogamediscovery.features.favorite_generes.data.remote.GenresRemoteDataSource
import com.thirdwayv.videogamediscovery.features.favorite_generes.domain.repository.IGenresRepository
import com.thirdwayv.videogamediscovery.features.favorite_generes.data.repository.GenresRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class GenresModule {

    @Binds
    abstract fun provideGenresRepository(repository: GenresRepository): IGenresRepository

    @Binds
    abstract fun provideGenresRemoteDataSourceImpl(
        remoteDataSource: GenresRemoteDataSource
    ): IGenresRemoteDataSource
}
package com.thirdwayv.videogamediscovery.features.main_activity.di

import com.thirdwayv.videogamediscovery.features.favorite_generes.data.remote.IGenresRemoteDataSource
import com.thirdwayv.videogamediscovery.features.favorite_generes.data.remote.GenresRemoteDataSource
import com.thirdwayv.videogamediscovery.features.favorite_generes.domain.repository.IGenresRepository
import com.thirdwayv.videogamediscovery.features.favorite_generes.data.repository.GenresRepository
import com.thirdwayv.videogamediscovery.features.main_activity.data.repository.MainRepo
import com.thirdwayv.videogamediscovery.features.main_activity.domain.repository.IMainRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun provideMainRepository(repository: MainRepo): IMainRepo
}
package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.di

import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.remote.IGamesRemoteDataSource
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.remote.IGamesRemoteDataSourceImpl
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.repository.GamesRepository
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.domain.repository.IGamesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class GamesModule {

    @Binds
    abstract fun provideGamesRepository(gamesRepository: GamesRepository): IGamesRepository

    @Binds
    abstract fun provideGamesRemoteDataSource(
        remoteDataSource: IGamesRemoteDataSourceImpl
    ): IGamesRemoteDataSource
}
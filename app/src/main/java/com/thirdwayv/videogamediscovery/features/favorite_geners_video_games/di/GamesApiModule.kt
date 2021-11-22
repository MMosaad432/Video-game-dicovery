package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.di

import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.remote.GamesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
class GamesApiModule {
    @Provides
    fun provideGamesApi(retrofit: Retrofit): GamesApiService {
        return retrofit.create(GamesApiService::class.java)
    }
}
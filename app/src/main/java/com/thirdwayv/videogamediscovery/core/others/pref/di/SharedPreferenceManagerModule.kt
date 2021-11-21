package com.thirdwayv.videogamediscovery.core.others.pref.di

import com.thirdwayv.videogamediscovery.core.others.pref.ISharedPreferenceManager
import com.thirdwayv.videogamediscovery.core.others.pref.SharedPreferenceManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SharedPreferenceManagerModule {

    @Binds
    abstract fun provideSharedPrefsStore(sharedPreferenceManager: SharedPreferenceManager): ISharedPreferenceManager

}

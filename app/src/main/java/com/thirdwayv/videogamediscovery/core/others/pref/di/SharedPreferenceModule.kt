package com.thirdwayv.videogamediscovery.core.others.pref.di

import android.content.Context
import android.content.SharedPreferences
import com.thirdwayv.videogamediscovery.core.others.pref.ISharedPreferenceManager
import com.thirdwayv.videogamediscovery.core.others.pref.SharedPreferenceManager
import com.thirdwayv.videogamediscovery.core.utils.SHARED_PREFERENCE_NAME
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceModule {
    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    }
}
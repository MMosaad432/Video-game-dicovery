package com.thirdwayv.videogamediscovery.features.main_activity.data.repository

import com.thirdwayv.videogamediscovery.core.others.pref.ISharedPreferenceManager
import com.thirdwayv.videogamediscovery.features.main_activity.domain.repository.IMainRepo
import com.thirdwayv.videogamediscovery.features.utils.FAVORITE_SELECTED
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val iLocalDataSource: ISharedPreferenceManager
) : IMainRepo {
    override fun getIsFavoriteSelected(): Boolean {
        return iLocalDataSource.getBoolean(FAVORITE_SELECTED)
    }
}
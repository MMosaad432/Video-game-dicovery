package com.thirdwayv.videogamediscovery.core.views.viewmodel

import com.thirdwayv.videogamediscovery.core.others.Message

interface ITriggerViews {
    fun showLoading()
    fun hideLoading()
    fun showSnackBarMessage(message: Message)
}
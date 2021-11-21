package com.thirdwayv.videogamediscovery.core.views.adapters

interface OnViewStatusChange {
    fun showLoading(b: Boolean)
    fun showError(errorModel: String){}
}
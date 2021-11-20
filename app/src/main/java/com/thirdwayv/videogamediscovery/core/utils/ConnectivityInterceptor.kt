package com.thirdwayv.videogamediscovery.core.utils

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor : Interceptor {

    lateinit var networkManager: NetworkManager

    @Throws(NoConnectivityException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!networkManager.isNetworkConnected()) {
            throw NoConnectivityException()
        } else {
            chain.proceed(chain.request())
        }
    }


    class NoConnectivityException : IOException()
}
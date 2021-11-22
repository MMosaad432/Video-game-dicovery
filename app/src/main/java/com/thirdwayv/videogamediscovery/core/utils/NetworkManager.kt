package com.thirdwayv.videogamediscovery.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkManager @Inject constructor(@ApplicationContext val context: Context) {

    fun isNetworkConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                val networkInfo = connectivityManager.activeNetwork ?: return false
                val networkCapabilities =
                    connectivityManager.getNetworkCapabilities(networkInfo) ?: return false
                when {
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
            else -> {
                // Use depreciated methods only on older devices
                val networkInfo = connectivityManager.activeNetworkInfo ?: return false
                networkInfo.isConnected


            }
        }
    }
}
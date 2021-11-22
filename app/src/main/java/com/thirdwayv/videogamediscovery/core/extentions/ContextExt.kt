package com.thirdwayv.videogamediscovery.core.extentions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

val Context.isConnected: Boolean
    get() {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
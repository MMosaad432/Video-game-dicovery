package com.thirdwayv.videogamediscovery.core.utils

import android.util.Log
import com.google.gson.JsonSyntaxException
import com.thirdwayv.videogamediscovery.core.others.CoroutineContextProvider
import com.thirdwayv.videogamediscovery.core.others.Resource
import com.thirdwayv.videogamediscovery.core.views.viewmodel.ITriggerViews
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

open class RetrofitCoroutines(private val iTriggerViews: ITriggerViews? = null) {

    /** set it true to handle errors coming from the server*/
    private var errorWillBeHandled: Boolean = false

    /**
     * this func called to handle exception coming from API
     * @return it return boolean to till us that the error
     * has been handled or no any action taken for this type of error
     * */
    private fun handleNetworkError(e: Throwable): Boolean {
        when {
            e is SocketTimeoutException -> {
                showError("Can't reach the server")
                return true
            }
            e is ConnectivityInterceptor.NoConnectivityException || e is UnknownHostException || e is ConnectException || e is SSLException || e is SocketException -> {

                showError("Network not available")
                return true
            }
            e is JsonSyntaxException -> {
                showError("Server error")
                return true
            }
            e is IOException -> {
                showError("Something went wrong")
                return true
            }
            else -> {
                return if (!errorWillBeHandled) {
                    showError("Unexpected Error")
                    true
                } else
                    false
            }
        }
    }

    private fun showError(msg: String) {
        iTriggerViews?.showSnackBarMessage(msg)
    }


    open suspend fun <T> networkCall(
        block: suspend () -> T,
        shouldShowLoading: Boolean = true,
        dispatcher: CoroutineContextProvider
    ): Resource<T> {
        return withContext(dispatcher.Main) {
            try {
                iTriggerViews?.showLoading()
                withContext(dispatcher.IO) {
                    val result = block.invoke()
                    return@withContext Resource.Success(result)
                }
            } catch (e: Throwable) {
                val errorHandled = handleNetworkError(e)
                Log.e("daddadddafaf", "networkCall: ${e.message}", )
                if (errorHandled) {
                    Resource.Failure(null)
                } else {
                    Resource.Failure(e.message)
                }
            } finally {
                iTriggerViews?.hideLoading()
            }
        }
    }

}
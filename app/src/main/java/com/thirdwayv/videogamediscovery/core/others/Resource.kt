package com.thirdwayv.videogamediscovery.core.others

sealed class Resource<out T>(val value: T?) {

    data class Success<out T>(val data: T) : Resource<T>(data)

    data class Failure(var message: String? = null) : Resource<Nothing>(null)

    object Loading : Resource<Nothing>(null)
}
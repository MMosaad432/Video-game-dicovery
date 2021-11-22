package com.thirdwayv.videogamediscovery.core.others.pref

interface ISharedPreferenceManager {
    fun saveString(key: String, value: String)
    fun getString(key: String): String

    fun <T> saveList(key: String, value: MutableList<T>)
    fun <T> getList(key: String): MutableList<T>

    fun saveBoolean(key: String, value: Boolean)
    fun getBoolean(key: String): Boolean
}
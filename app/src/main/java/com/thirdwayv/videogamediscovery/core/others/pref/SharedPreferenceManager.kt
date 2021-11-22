package com.thirdwayv.videogamediscovery.core.others.pref

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) :
    ISharedPreferenceManager {

    override fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    override fun <T> saveList(key: String, value: MutableList<T>) {
        val listToJson = gson.toJson(value)
        saveString(key, listToJson)
    }

    override fun <T> getList(key: String): MutableList<T> {
        val json = getString(key)
        val type: Type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(json, type)?: mutableListOf()
    }

    override fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
}
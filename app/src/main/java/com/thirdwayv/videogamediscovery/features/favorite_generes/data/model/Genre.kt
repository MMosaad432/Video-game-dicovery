package com.thirdwayv.videogamediscovery.features.favorite_generes.data.model

import com.google.gson.annotations.SerializedName

data class Genre(
    val id: Int,
    val name: String?,
    val slug: String?,
    @SerializedName("games_count") val gamesCount: Int?,
    @SerializedName("image_background") val imageBackground: String?,
    var isFavorite: Boolean = false
)
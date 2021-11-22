package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("background_image") val backgroundImage: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("ratings_count") val ratingsCount: Int
)
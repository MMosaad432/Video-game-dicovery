package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model

import com.google.gson.annotations.SerializedName

data class GamesResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: List<Game>
)

package com.thirdwayv.videogamediscovery.features.favorite_generes.data.model

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("results")
    val genres : List<Genre>
)
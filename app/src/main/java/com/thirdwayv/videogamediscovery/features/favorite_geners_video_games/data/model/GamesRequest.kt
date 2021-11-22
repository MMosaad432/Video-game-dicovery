package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model

data class GamesRequest(
    var favoriteGenresIds: List<Int>? = null,
    var page: Int? = 1,
    var pageSize: Int? = 20
)

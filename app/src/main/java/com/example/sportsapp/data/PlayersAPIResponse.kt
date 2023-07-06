package com.example.sportsapp.data

import com.squareup.moshi.Json
import java.io.Serializable

data class PlayersAPIResponse(
    @Json(name = "player")
    val players: List<Player>?
)

data class Player(
    val idPlayer: String,
    val strPlayer: String?,
    val strTeam: String?,
    val strSport: String?,
    val strDescriptionEN: String?,
    val strPosition: String?,
    val strTwitter: String?,
    val strInstagram: String?,
    val strThumb: String?
) : Serializable


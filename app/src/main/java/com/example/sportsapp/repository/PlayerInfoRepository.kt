package com.example.sportsapp.repository

import com.example.sportsapp.data.PlayersAPIResponse

interface PlayerInfoRepository {
    suspend fun getAllPlayers(team: String): PlayerInfoResult
}

sealed class PlayerInfoResult {
    data class Success(val playerInfoResult: PlayersAPIResponse) : PlayerInfoResult()
    data class Failure(val message: Exception) : PlayerInfoResult()
}
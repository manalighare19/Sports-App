package com.example.sportsapp.network

import com.example.sportsapp.data.PlayersAPIResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("searchplayers.php")
    suspend fun getAllPlayers(
        @Query("t") team: String,
    ): PlayersAPIResponse
}
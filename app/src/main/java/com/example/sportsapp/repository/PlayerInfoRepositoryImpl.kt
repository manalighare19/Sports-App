package com.example.sportsapp.repository

import com.example.sportsapp.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlayerInfoRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val apiService: ApiService
) : PlayerInfoRepository {
    // Fetch list of all players
    override suspend fun getAllPlayers(team: String): PlayerInfoResult =
        withContext(ioDispatcher) {
            try {
                val response = apiService.getAllPlayers(team)
                PlayerInfoResult.Success(response)
            } catch (e: Exception) {
                PlayerInfoResult.Failure(e)
            }
        }
}
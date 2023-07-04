package com.example.sportsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsapp.repository.PlayerInfoRepository
import com.example.sportsapp.repository.PlayerInfoResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersListViewModel @Inject constructor(private val repository: PlayerInfoRepository) : ViewModel() {

     fun fetchPlayersInfo(team: String) {
        viewModelScope.launch {
            when (val response = repository.getAllPlayers(team)) {
                is PlayerInfoResult.Success -> {
                    print("response is: $response")
                }
                is PlayerInfoResult.Failure -> {
                    print("error is: ${response.message}")
                }
            }
        }
    }
}
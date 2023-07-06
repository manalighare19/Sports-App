package com.example.sportsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsapp.data.PlayersAPIResponse
import com.example.sportsapp.repository.PlayerInfoRepository
import com.example.sportsapp.repository.PlayerInfoResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersListViewModel @Inject constructor(private val repository: PlayerInfoRepository) : ViewModel() {

    // List of all team players
    private val _result = MutableLiveData<UiState>(UiState.Empty)
    val result: LiveData<UiState> = _result

     fun fetchPlayersInfo(team: String) {
        viewModelScope.launch {
            _result.value = UiState.Loading
            when (val response = repository.getAllPlayers(team)) {
                is PlayerInfoResult.Success -> {
                    if (response.playerInfoResult.players.isNullOrEmpty()) {
                        _result.value = UiState.Empty
                    } else {
                        _result.value = UiState.Data(response.playerInfoResult)
                    }
                }
                is PlayerInfoResult.Failure -> {
                    _result.value = UiState.Failure
                }
            }
        }
    }

    sealed class UiState {
        // Page Starting state
        object Init : UiState()

        // Empty State
        object Empty : UiState()

        // Loading Data
        object Loading : UiState()

        // Successfully loaded data
        data class Data(val playersList: PlayersAPIResponse) : UiState()

        // Failed to load data
        object Failure : UiState()
    }
}
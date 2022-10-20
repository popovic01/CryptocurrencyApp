package com.plcoding.cryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyapp.common.Resource
import com.plcoding.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val repository: CoinRepository
): ViewModel() {
     //main purpose of view model is to maintain the state
     var state by mutableStateOf(CoinListState()) //mutable state

    init {
        getCoins()
    }

    fun onEvent(event: CoinListEvent) {
        when(event) {
            is CoinListEvent.Refresh -> {
                getCoins(fetchFromRemote = true)
            }
        }
    }

    //function calls get coins use case and puts the result in state object to display that in UI
    private fun getCoins(fetchFromRemote: Boolean = false) {
        //we need to launch flow in coroutine (flow is asynchronous)
        viewModelScope.launch {
            repository
                .getCoins(fetchFromRemote)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { coins ->
                                state = state.copy(coins = coins)
                            }
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading, error = null)
                        }
                        is Resource.Error -> {
                            state = state.copy(error = result.message ?: "", isLoading = false)
                        }
                    }
                }
        }
    }
}


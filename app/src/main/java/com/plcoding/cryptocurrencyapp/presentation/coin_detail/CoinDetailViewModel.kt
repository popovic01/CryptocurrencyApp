package com.plcoding.cryptocurrencyapp.presentation.coin_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyapp.common.Constants
import com.plcoding.cryptocurrencyapp.common.Resource
import com.plcoding.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    //contains info about the saved state
    savedStateHandle: SavedStateHandle,
    private val repository: CoinRepository
): ViewModel() {
    var state by mutableStateOf(CoinDetailState())
    lateinit var coinId: String

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { id ->
            coinId = id
            getCoin(coinId)
        }
    }

    fun onEvent(event: CoinDetailEvent) {
        when(event) {
            is CoinDetailEvent.Refresh -> {
                getCoin(coinId, true)
            }
        }
    }

    private fun getCoin(coinId: String, fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            repository
                .getCoinById(coinId, fetchFromRemote)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { coin ->
                                state = state.copy(coin = coin, error = "")
                            }
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Error -> {
                            state = state.copy(error = result.message ?: "", isLoading = false, coin = null)
                        }
                    }
                }
        }
    }
}
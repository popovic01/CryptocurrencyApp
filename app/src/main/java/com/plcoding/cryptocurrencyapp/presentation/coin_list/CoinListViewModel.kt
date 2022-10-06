package com.plcoding.cryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyapp.common.Resource
import com.plcoding.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {
     //main purpose of viewmodel is to maintain the state
    private val _state = mutableStateOf<CoinListState>(CoinListState()) //mutable state
    val state: State<CoinListState> = _state //immutable state
    /*we have the public and the private version of the state because wa don't want
    to be able to modify the content of this state in our composables*/

    init {
        getCoins()
    }

    //function calls get coins use case and puts the result in state object to display that in UI
    private fun getCoins() {
        //it returns the flow, that emits the resource value over time
        //we call getCoinsUseCase like function because we override the invoke function
        getCoinsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?:
                    "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
        //we need to launch flow in coroutine (flow is asynchronous)
    }
}


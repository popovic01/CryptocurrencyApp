package com.plcoding.cryptocurrencyapp.presentation.coin_list

sealed class CoinListEvent {
    object Refresh: CoinListEvent()
}

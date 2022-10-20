package com.plcoding.cryptocurrencyapp.presentation.coin_detail

sealed class CoinDetailEvent {
    object Refresh: CoinDetailEvent()
}

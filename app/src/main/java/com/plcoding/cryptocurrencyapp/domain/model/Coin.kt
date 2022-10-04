package com.plcoding.cryptocurrencyapp.domain.model

//this contain coin data we display in UI
data class Coin(
    val id: String,
    val is_active: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)

package com.plcoding.cryptocurrencyapp.data.remote.dto

//dto - Data Transfer Object, object we get from our api
//data class that represents coin
data class CoinDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)



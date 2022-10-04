package com.plcoding.cryptocurrencyapp.data.remote.dto

import com.plcoding.cryptocurrencyapp.domain.model.Coin

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

//function that maps coin dto to coin (data we need in our UI)
fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        is_active = is_active,
        name = name,
        rank = rank,
        symbol = symbol
    )
}
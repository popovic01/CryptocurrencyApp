package com.plcoding.cryptocurrencyapp.domain.repository

import com.plcoding.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyapp.data.remote.dto.CoinDto

//repository in domain layer - creating an interface
//function for each api request
interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}
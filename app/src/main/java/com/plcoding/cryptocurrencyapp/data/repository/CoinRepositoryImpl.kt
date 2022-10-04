package com.plcoding.cryptocurrencyapp.data.repository

import com.plcoding.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyapp.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

//repository in data layer - implementation of our repository interface
//contains direct access to our data
//we inject dependency - api
class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi): CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}


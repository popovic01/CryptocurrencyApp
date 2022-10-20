package com.plcoding.cryptocurrencyapp.domain.repository

import com.plcoding.cryptocurrencyapp.common.Resource
import com.plcoding.cryptocurrencyapp.domain.model.Coin
import com.plcoding.cryptocurrencyapp.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow

//repository in domain layer - interface of a repository
//function for each api request
interface CoinRepository {
    suspend fun getCoins(fetchFromRemote: Boolean): Flow<Resource<List<Coin>>>
    suspend fun getCoinById(coinId: String, fetchFromRemote: Boolean): Flow<Resource<CoinDetail>>
}
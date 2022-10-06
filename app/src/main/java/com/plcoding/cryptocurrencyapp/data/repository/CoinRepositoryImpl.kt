package com.plcoding.cryptocurrencyapp.data.repository

import com.plcoding.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyapp.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyapp.domain.model.Coin
import com.plcoding.cryptocurrencyapp.domain.model.CoinDetail
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

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coin_id = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        is_active = is_active,
        tags = tags.map { it.name },
        team = team
    )
}


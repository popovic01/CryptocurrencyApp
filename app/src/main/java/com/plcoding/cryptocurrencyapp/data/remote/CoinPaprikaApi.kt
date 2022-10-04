package com.plcoding.cryptocurrencyapp.data.remote

import com.plcoding.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

//functions and routes we want to access from our api
interface CoinPaprikaApi {
    //get coins
    @GET("v1/coins")
    //susepend - we can execute it in a coroutine asynchronously
    suspend fun getCoins(): List<CoinDto>

    //get details about a specific coin
    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
}



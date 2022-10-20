package com.plcoding.cryptocurrencyapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface CoinDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertCoins(coins: List<CoinEntity>)

    @Query("DELETE FROM coinentity")
    suspend fun clearCoins()

    @Query("SELECT * FROM coinentity")
    suspend fun getCoins(): List<CoinEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insertCoinDetail(coin: CoinDetailEntity)

    @Query("DELETE FROM coindetailentity WHERE coin_id = :coinId")
    suspend fun clearCoinDetail(coinId: String)

    @Query("SELECT * FROM coindetailentity WHERE coin_id = :coinId")
    suspend fun getCoinDetail(coinId: String): CoinDetailEntity?
}
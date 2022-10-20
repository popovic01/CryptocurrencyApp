package com.plcoding.cryptocurrencyapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoinEntity(
    val id: String,
    val is_active: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    @PrimaryKey val coinId: Int? = null
)
package com.plcoding.cryptocurrencyapp.data.mapper

import android.util.Log
import com.plcoding.cryptocurrencyapp.data.local.CoinDetailEntity
import com.plcoding.cryptocurrencyapp.data.local.CoinEntity
import com.plcoding.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyapp.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyapp.domain.model.Coin
import com.plcoding.cryptocurrencyapp.domain.model.CoinDetail

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

fun CoinEntity.toCoin(): Coin {
    return Coin(
        id = id,
        is_active = is_active,
        name = name,
        rank = rank,
        symbol = symbol
    )
}

fun Coin.toCoinEntity(): CoinEntity {
    return CoinEntity(
        id = id,
        is_active = is_active,
        name = name,
        rank = rank,
        symbol = symbol
    )
}

fun CoinDetailEntity.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coin_id = coin_id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        is_active = is_active,
        tags = tags,
        team = team.map { it.toTeamMember()  }
    )
}

fun CoinDetail.toCoinDetailEntity(): CoinDetailEntity {
    return CoinDetailEntity(
        coin_id = coin_id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        is_active = is_active,
        tags = tags,
        team = team.map { it.toTeamMemberEntity() }
    )
}

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    val tagsNull = if (tags != null) tags.map { it.name } else emptyList()
    return CoinDetail(
        coin_id = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        is_active = is_active,
        tags = tagsNull,
        team = team
    )
}
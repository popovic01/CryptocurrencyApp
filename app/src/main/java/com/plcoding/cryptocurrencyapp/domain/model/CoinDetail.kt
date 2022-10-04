package com.plcoding.cryptocurrencyapp.domain.model

import com.plcoding.cryptocurrencyapp.data.remote.dto.TeamMember

data class CoinDetail(
    val coin_id: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val is_active: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>
)

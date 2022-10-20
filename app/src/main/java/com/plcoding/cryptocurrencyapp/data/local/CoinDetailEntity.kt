package com.plcoding.cryptocurrencyapp.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.plcoding.cryptocurrencyapp.data.local.converter.TeamConverter
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class CoinDetailEntity(
    val coin_id: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val is_active: Boolean,
    val tags: List<String>,
    @TypeConverters(TeamConverter::class)
    val team: List<TeamMemberEntity>,
    @PrimaryKey val id: Int? = null
) : Parcelable

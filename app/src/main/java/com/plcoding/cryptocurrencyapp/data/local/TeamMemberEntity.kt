package com.plcoding.cryptocurrencyapp.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class TeamMemberEntity(
    val member_id: String,
    val name: String,
    val position: String,
    @PrimaryKey val id: Int? = null
) : Parcelable
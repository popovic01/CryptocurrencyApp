package com.plcoding.cryptocurrencyapp.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.plcoding.cryptocurrencyapp.data.local.TeamMemberEntity

class TeamConverter {

    @TypeConverter
    fun jsonStringToList(members: String?): List<TeamMemberEntity>? {
        return if (members == null) {
            null
        } else {
            val type = object: TypeToken<List<TeamMemberEntity>>() {

            }.type
            Gson().fromJson(members, type)
        }
    }

    @TypeConverter
    fun listToJsonString(members: List<TeamMemberEntity?>?): String? {
        return if (members == null) {
            null
        } else {
            val type = object: TypeToken<List<TeamMemberEntity>>() {

            }.type
            Gson().toJson(members, type)
        }
    }
}
package com.plcoding.cryptocurrencyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.plcoding.cryptocurrencyapp.data.local.converter.TagsConverter
import com.plcoding.cryptocurrencyapp.data.local.converter.TeamConverter

@Database(
    entities = [CoinEntity::class, CoinDetailEntity::class, TeamMemberEntity::class], version = 1
)
@TypeConverters(TagsConverter::class, TeamConverter::class)
abstract class CoinDatabase: RoomDatabase() {
    abstract val dao: CoinDao
}
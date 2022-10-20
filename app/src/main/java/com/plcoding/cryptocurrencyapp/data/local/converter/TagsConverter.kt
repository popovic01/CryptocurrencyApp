package com.plcoding.cryptocurrencyapp.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

class TagsConverter {

    //Room doesn't know how to save list in db, so we need type converters for that

    //reading from db
    @TypeConverter
    fun jsonStringToList(tags: String) = Gson().fromJson(tags, Array<String>::class.java).toList()

    //saving in db
    @TypeConverter
    fun listToJsonString(tags: List<String>?) = Gson().toJson(tags)
}
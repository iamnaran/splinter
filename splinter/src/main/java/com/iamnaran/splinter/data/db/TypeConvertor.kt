package com.iamnaran.splinter.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iamnaran.splinter.data.entity.EventStatus

class TypeConvertor {

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromEnum(value: EventStatus): String {
        return value.name
    }

    @TypeConverter
    fun toEnum(value: String): EventStatus {
        return EventStatus.valueOf(value)
    }


}
package com.iamnaran.splinter.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Utils {

    private val gson = Gson()

    // Convert from JSON to object
    fun <T> String.fromJson(): T? {
        return try {
            gson.fromJson(this, object : TypeToken<T>() {}.type)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // Convert from object to JSON
    fun Any.toJson(): String {
        return gson.toJson(this)
    }

}
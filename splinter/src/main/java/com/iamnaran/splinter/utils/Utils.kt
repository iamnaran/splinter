package com.iamnaran.splinter.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal object Utils {

    val gson = Gson()

    // Convert from JSON to object

    inline fun <reified T> String.fromJson(): T? {
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
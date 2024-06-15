package com.iamnaran.splinter.preference

import android.content.Context
import android.content.SharedPreferences
import com.iamnaran.splinter.model.SpEvent
import com.iamnaran.splinter.utils.SplinterGson

class PreferenceManager(context: Context)  {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PrefConstants.PREF_FILE_NAME, Context.MODE_PRIVATE)

    fun saveSplinterEvents(splinterEvents: List<SpEvent>) {
        val json: String = SplinterGson.gsonBuilder().create().toJson(splinterEvents)
        sharedPreferences.edit().putString(PrefConstants.SPLINTER_EVENTS, json).apply()
    }

    fun getSplinterEvents(): List<SpEvent> {
        val json = sharedPreferences.getString(PrefConstants.SPLINTER_EVENTS, null)
        return if (json != null) {
            SplinterGson.gsonBuilder().create().fromJson(json, Array<SpEvent>::class.java).toList()
        } else {
            emptyList()
        }
    }


}
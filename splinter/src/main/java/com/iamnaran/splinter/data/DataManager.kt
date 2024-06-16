package com.iamnaran.splinter.data

import android.content.Context
import androidx.room.Room
import com.iamnaran.splinter.data.db.SplinterDatabase
import com.iamnaran.splinter.data.entity.Event
import com.iamnaran.splinter.utils.SPLINTER_DATABASE_NAME
import kotlinx.coroutines.flow.Flow

class DataManager private constructor(private val splinterDatabase: SplinterDatabase) {

    companion object {
        @Volatile
        private var INSTANCE: DataManager? = null

        @JvmStatic
        fun getInstance(context: Context): DataManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: run {
                    val db = Room.databaseBuilder(
                        context,
                        SplinterDatabase::class.java, SPLINTER_DATABASE_NAME
                    ).build()
                    DataManager(db).also { INSTANCE = it }
                }
            }
        }
    }

    fun getAllSpEvents() : Flow<List<Event>> {
        return splinterDatabase.spEventDao().getAllSpEvents()
    }

    fun updateSpEventById() : Flow<List<Event>> {
        return splinterDatabase.spEventDao().getAllSpEvents()
    }

    fun deleteSpEvents() : Flow<List<Event>> {
        return splinterDatabase.spEventDao().getAllSpEvents()
    }

    fun deleteSpEventById() : Flow<List<Event>> {
        return splinterDatabase.spEventDao().getAllSpEvents()
    }
}
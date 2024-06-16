package com.iamnaran.splinter.data

import android.content.Context
import androidx.room.Room
import com.iamnaran.splinter.data.entity.Event
import kotlinx.coroutines.flow.Flow

class DataManager private constructor(private val appDatabase: AppDatabase) {

    companion object {
        @Volatile
        private var INSTANCE: DataManager? = null

        @JvmStatic
        fun getInstance(context: Context): DataManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: run {
                    val db = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java, "database-name"
                    ).build()
                    DataManager(db).also { INSTANCE = it }
                }
            }
        }
    }

    fun getAllSpEvents() : Flow<List<Event>> {
        return appDatabase.spEventDao().getAllSpEvents()
    }

    fun updateSpEventById() : Flow<List<Event>> {
        return appDatabase.spEventDao().getAllSpEvents()
    }

    fun deleteSpEvents() : Flow<List<Event>> {
        return appDatabase.spEventDao().getAllSpEvents()
    }

    fun deleteSpEventById() : Flow<List<Event>> {
        return appDatabase.spEventDao().getAllSpEvents()
    }
}
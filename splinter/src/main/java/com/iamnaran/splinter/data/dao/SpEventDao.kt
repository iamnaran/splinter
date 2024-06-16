package com.iamnaran.splinter.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iamnaran.splinter.data.entity.SpEvent
import kotlinx.coroutines.flow.Flow

@Dao
interface  SpEventDao {

    @Query("SELECT * FROM user WHERE id = :id")
    fun getSpEventById(id: Long): Flow<SpEvent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpEvent(spEvent: SpEvent)

    @Query("SELECT * FROM user")
    fun getAllSpEvents(): Flow<List<SpEvent>>

}
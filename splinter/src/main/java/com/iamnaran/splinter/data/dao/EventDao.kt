package com.iamnaran.splinter.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iamnaran.splinter.data.entity.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface  EventDao {

    @Query("SELECT * FROM user WHERE id = :id")
    fun getSpEventById(id: Long): Flow<Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpEvent(event: Event)

    @Query("SELECT * FROM user")
    fun getAllSpEvents(): Flow<List<Event>>

}
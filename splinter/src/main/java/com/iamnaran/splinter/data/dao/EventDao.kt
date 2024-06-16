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
    suspend fun getEventById(id: Long): Flow<Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Query("SELECT * FROM user")
    fun getAllEvents(): Flow<List<Event>>

    // getAllEventsBySessionId
    @Query("SELECT * FROM user WHERE sessionId = :sessionId")
    suspend fun getAllEventsBySessionId(sessionId: String): Flow<List<Event>>

}
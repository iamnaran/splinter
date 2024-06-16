package com.iamnaran.splinter.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.iamnaran.splinter.data.entity.SessionWithEvents

@Dao
interface SessionDao {
    @Transaction
    @Query("SELECT * FROM sessions")
    fun getSessionsWithEvents(): List<SessionWithEvents>
}
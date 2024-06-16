package com.iamnaran.splinter.data.repo

import com.iamnaran.splinter.data.dao.EventDao
import com.iamnaran.splinter.data.entity.Event
import kotlinx.coroutines.flow.Flow

class EventRepository(private val eventDao: EventDao) {

    suspend fun insertSession(event: Event) {
        eventDao.insert(event)

    }

    suspend fun getAllEventsBySessionId(sessionId: String): Flow<List<Event>> {
        return eventDao.getAllEventsBySessionId(sessionId)
    }


}
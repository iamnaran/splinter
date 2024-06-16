package com.iamnaran.splinter.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class SessionWithEvents(
    @Embedded val session: Session,
    @Relation(
        parentColumn = "sessionId",
        entityColumn = "sessionId"
    )
    val events: List<Event>
)
package com.iamnaran.splinter.data.model

import com.iamnaran.splinter.utils.toHumanReadableDate
import java.util.UUID

data class Event(
    val sessionId: String = generateRandomId(),
    val name: String,
    val properties: String,
    val eventStatus: EventStatus,
    val createdAt: Long = System.currentTimeMillis(),
    val formattedDate: String = createdAt.toHumanReadableDate()
)

private fun generateRandomId(): String {
    return UUID.randomUUID().toString()
}


enum class EventStatus {
    CACHED,
    DELIVERED
}





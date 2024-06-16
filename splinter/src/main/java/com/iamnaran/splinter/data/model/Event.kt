package com.iamnaran.splinter.data.model

import java.util.UUID

data class Event(
    val sessionId: String = generateRandomId(),
    val name: String,
    val properties: String,
    val eventStatus: EventStatus,
    val createdAt: Long = System.currentTimeMillis(),
)

private fun generateRandomId(): String {
    return UUID.randomUUID().toString()
}


enum class EventStatus {
    CACHED,
    DELIVERED
}





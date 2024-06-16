package com.iamnaran.splinter.data.model

data class Event(
    val eventId: Int,
    val sessionId: String,
    val name: String,
    val properties: String,
    val eventStatus: EventStatus,
    val createdAt: String,
)

enum class EventStatus {
    CACHED,
    DELIVERED
}





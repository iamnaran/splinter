package com.iamnaran.splinter.model

data class SpEvent(
    val id: String,
    val name: String,
    val properties: Map<String, Any>,
    val timestamp: Long,
    var status: SpEventStatus = SpEventStatus.CACHED
)

enum class SpEventStatus {
    CACHED,
    DELIVERED
}






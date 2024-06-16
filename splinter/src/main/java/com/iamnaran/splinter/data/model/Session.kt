package com.iamnaran.splinter.data.model

import java.util.UUID

data class Session(
    val id: String = generateRandomId(),
    val startTime: Long = System.currentTimeMillis()
) {
    fun isActive(timeOut: Long): Boolean {
        return System.currentTimeMillis() < startTime + timeOut
    }
}


private fun generateRandomId(): String {
    return UUID.randomUUID().toString()
}






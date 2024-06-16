package com.iamnaran.splinter.data.model

data class Session(val id: String, val startTime: Long) {
    fun isActive(timeOut: Long): Boolean {
        return System.currentTimeMillis() < startTime + timeOut
    }
}





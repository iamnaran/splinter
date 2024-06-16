package com.iamnaran.splinter.core

data class Config(
    var dispatchIntervalHours: Long = com.iamnaran.splinter.utils.dispatchIntervalHours,
    var maxCachedEvents: Int = com.iamnaran.splinter.utils.maxCachedEvents
)
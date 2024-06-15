package com.iamnaran.splinter.core

data class SplinterConfig(
    var dispatchIntervalHours: Long = com.iamnaran.splinter.utils.dispatchIntervalHours,
    var maxCachedEvents: Int = com.iamnaran.splinter.utils.maxCachedEvents
)
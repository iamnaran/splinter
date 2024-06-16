package com.iamnaran.splinter.core

import com.iamnaran.splinter.utils.Constants

data class Config(
    var dispatchIntervalHours: Long = Constants.DISPATCH_INTERVAL_DURATION_IN_MINUTE,
    var maxCachedEvents: Int = Constants.MAX_CACHED_EVENTS,
    var sessionTimeOutDurationInMinute: Long = Constants.SESSION_TIME_OUT_DURATION_IN_MINUTE
)
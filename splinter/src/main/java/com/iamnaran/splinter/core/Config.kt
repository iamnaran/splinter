package com.iamnaran.splinter.core

import com.iamnaran.splinter.utils.Constants

/**
 * Data class representing the configuration settings for the splinter agent.
 *
 * @property splinterAPIKey The API key for accessing the Splinter API.
 * @property splinterAPISecret The API secret for accessing the Splinter API.
 * @property dispatchIntervalHours The interval in hours at which events are dispatched.
 * @property maxCachedEvents The maximum number of events that can be cached before dispatch.
 * @property sessionTimeOutDurationInMinute The duration in minutes for session timeout.
 */
data class Config(
    var splinterAPIKey: String,
    var splinterAPISecret: String,
    var dispatchIntervalHours: Long = Constants.DISPATCH_INTERVAL_DURATION_IN_MINUTE,
    var maxCachedEvents: Int = Constants.MAX_CACHED_EVENTS,
    var sessionTimeOutDurationInMinute: Long = Constants.SESSION_TIME_OUT_DURATION_IN_MINUTE
) {
    fun isValid(): Boolean {
        return dispatchIntervalHours > 5 && maxCachedEvents > 10 && sessionTimeOutDurationInMinute > 0 && isValidTimeOutDuration()
    }

    private fun isValidTimeOutDuration(): Boolean {
        return (sessionTimeOutDurationInMinute > 0)
    }
}
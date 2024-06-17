package com.iamnaran.splinter.utils

import android.util.Log
import android.util.Log.WARN


/**
 * Class for handling logging in the application with different log levels.
 */
object SplinterLog {

    // The minimum log level to be logged. Default is WARN.
    private var sMinLevel = WARN


    /**
     * Sets the minimum log level.
     * @param splinterLogType The log level to be set.
     */
    fun setLevel(splinterLogType: SplinterLogType) {
        sMinLevel = splinterLogType.ordinal
    }

    fun debug(tag: String, msg: String) {
        if (shouldLog(SplinterLogType.BASIC)) {
            Log.d(tag, msg)
        }
    }

    fun info(tag: String, msg: String) {
        if (shouldLog(SplinterLogType.INFO)) {
            Log.i(tag, msg)
        }
    }


    private fun shouldLog(level: SplinterLogType): Boolean {
        return sMinLevel <= level.ordinal
    }


}

enum class SplinterLogType {
    NONE,
    BASIC,
    INFO,
    ERROR,
}
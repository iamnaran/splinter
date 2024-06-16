package com.iamnaran.splinter.sample

import android.app.Application
import com.iamnaran.splinter.core.Config
import com.iamnaran.splinter.core.SplinterSdk
import com.iamnaran.splinter.utils.sessionTimeOutDurationInMinute

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize Splinter SDK

        val config = Config().apply {
            dispatchIntervalHours = 2L
            maxCachedEvents = 100
            sessionTimeOutDurationInMinute = 1
        }
        SplinterSdk.getInstance(this, config)

    }
}
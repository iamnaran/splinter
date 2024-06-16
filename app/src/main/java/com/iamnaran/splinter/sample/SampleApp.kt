package com.iamnaran.splinter.sample

import android.app.Application
import com.iamnaran.splinter.core.Config
import com.iamnaran.splinter.core.SplinterAgent

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize Splinter SDK

        val config = Config("YOUR_API_KEY","YOUR_API_SECRET").apply {
            dispatchIntervalHours = 2L
            maxCachedEvents = 100
            sessionTimeOutDurationInMinute = 1
        }
        SplinterAgent.getInstance(this, config)

    }
}
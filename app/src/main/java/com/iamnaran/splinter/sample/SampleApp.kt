package com.iamnaran.splinter.sample

import android.app.Application
import com.iamnaran.splinter.core.Config
import com.iamnaran.splinter.core.Splinter
import com.iamnaran.splinter.core.SplinterAgent

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize Splinter SDK

        val splinterConfig = Config("YOUR_API_KEY", "YOUR_API_SECRET").apply {
            dispatchIntervalDurationInMinute = 2L
            maxCachedEvents = 100
            sessionTimeOutDurationInMinute = 1
        }
        val splinterAgent = SplinterAgent.getInstance(this)
        splinterAgent.initialize(splinterConfig)
    }
}
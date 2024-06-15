package com.iamnaran.splinter.sample

import android.app.Application
import com.iamnaran.splinter.core.SplinterConfig
import com.iamnaran.splinter.core.SplinterSdk

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize Splinter SDK

        val config = SplinterConfig().apply {
            dispatchIntervalHours = 2L
            maxCachedEvents = 100
        }
        SplinterSdk.getInstance(this, config)
    }
}
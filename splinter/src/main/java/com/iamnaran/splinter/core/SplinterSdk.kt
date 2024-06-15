package com.iamnaran.splinter.core

import android.content.Context
import com.iamnaran.splinter.model.SpEvent
import com.iamnaran.splinter.model.SpEventStatus
import com.iamnaran.splinter.preference.PreferenceManager
import java.lang.ref.WeakReference
import java.util.UUID

object SplinterSdk {

    private var instance: SplinterSdk? = null
    private var contextRef: WeakReference<Context>? = null
    private lateinit var sharedPrefManager: PreferenceManager
    private lateinit var config: SplinterConfig

    fun getInstance(context: Context, splinterConfig: SplinterConfig): SplinterSdk {
        if (instance == null) {
            synchronized(this) {
                if (instance == null) {
                    instance = SplinterSdk
                    init(context, splinterConfig)
                }
            }
        }
        return instance!!
    }

    private fun init(context: Context, config: SplinterConfig) {
        contextRef = WeakReference(context.applicationContext)
        sharedPrefManager = PreferenceManager(context)
        this.config = config
    }

    fun logSplinterEvent(eventName: String, properties: Map<String, Any> = emptyMap()) {
        val event = SpEvent(
            id = generateEventId(),
            name = eventName,
            properties = properties,
            timestamp = System.currentTimeMillis(),
            status = SpEventStatus.CACHED
        )
        saveEventToCache(event)
    }

    private fun saveEventToCache(event: SpEvent) {


    }

    private fun sendCachedEventsToServer() {


    }


    private fun generateEventId(): String {
        return UUID.randomUUID().toString()
    }


    fun getConfigDispatchIntervalHours(): Long {
        return config.dispatchIntervalHours
    }

    fun getConfigMaxCachedEvents(): Int {
        return config.maxCachedEvents
    }


}
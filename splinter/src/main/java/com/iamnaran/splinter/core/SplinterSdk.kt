package com.iamnaran.splinter.core

import android.content.Context
import com.iamnaran.splinter.data.DataManager
import java.lang.ref.WeakReference
import java.util.UUID

object SplinterSdk {

    private var instance: SplinterSdk? = null
    private var contextRef: WeakReference<Context>? = null
    private lateinit var dataManager: DataManager
    private lateinit var config: Config

    fun getInstance(context: Context, config: Config): SplinterSdk {
        if (instance == null) {
            synchronized(this) {
                if (instance == null) {
                    instance = SplinterSdk
                    init(context, config)
                }
            }
        }
        return instance!!
    }

    private fun init(context: Context, config: Config) {
        contextRef = WeakReference(context.applicationContext)
        dataManager = DataManager.getInstance(context)
        this.config = config
    }

    fun logSplinterEvent(eventName: String, properties: Map<String, Any> = emptyMap()) {


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
package com.iamnaran.splinter.core

import android.content.Context
import com.iamnaran.splinter.data.PrefDataStoreManager
import com.iamnaran.splinter.data.model.Event
import com.iamnaran.splinter.data.model.EventStatus
import com.iamnaran.splinter.data.model.Session
import com.iamnaran.splinter.utils.CoroutineDispatcherProvider
import com.iamnaran.splinter.utils.Utils.toJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import java.util.UUID

object SplinterAgent {

    private var instance: SplinterAgent? = null
    private var contextRef: WeakReference<Context>? = null
    private lateinit var prefDataStoreManager: PrefDataStoreManager
    private lateinit var config: Config
    private var currentSession: Session? = null

    fun getInstance(context: Context, config: Config): SplinterAgent {
        if (instance == null) {
            synchronized(this) {
                if (instance == null) {
                    instance = SplinterAgent
                    init(context, config)
                }
            }
        }
        return instance!!
    }

    private fun init(context: Context, config: Config) {
        contextRef = WeakReference(context.applicationContext)
        prefDataStoreManager = PrefDataStoreManager.getInstance(context)
        this.config = config
        startSession()
    }

    private fun startSession() {
        if (currentSession != null) {
            if (!currentSession!!.isActive(config.sessionTimeOutDurationInMinute)) {
                currentSession = createSession()
            }
        } else {
            currentSession = createSession()
        }
    }

    private fun createSession(): Session {
        return Session()
    }

    fun logSplinterEvent(eventName: String, properties: Map<String, Any> = emptyMap()) {
        if (contextRef == null) {
            throw IllegalStateException("SplinterAgent not initialized")
        }
        if (currentSession == null) {
            startSession()
        }

        val newEvent = Event(
            name = eventName,
            properties = properties.toJson(),
            eventStatus = EventStatus.CACHED
        )

        CoroutineScope(CoroutineDispatcherProvider.IO).launch {
            prefDataStoreManager.addEventToCache(newEvent)
        }

    }


    fun setGroupProfile() {

    }


    fun setProfile() {

    }


    fun getActiveSessionId(): String {
        return currentSession!!.id
    }


    fun getConfigDispatchIntervalHours(): Long {
        return config.dispatchIntervalHours
    }

    fun getConfigMaxCachedEvents(): Int {
        return config.maxCachedEvents
    }


}
package com.iamnaran.splinter.core

import android.content.Context
import com.iamnaran.splinter.data.PrefDataStoreManager
import com.iamnaran.splinter.data.model.Session
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
        return Session(generateRandomId(), System.currentTimeMillis())
    }

    fun logSplinterEvent(eventName: String, properties: Map<String, Any> = emptyMap()) {

        if (currentSession != null){


        }else{
            startSession()
        }

    }


    fun setGroupProfile(){

    }


    fun setProfile(){

    }


    fun getActiveSessionId(): String {
        return currentSession!!.id
    }


    private fun generateRandomId(): String {
        return UUID.randomUUID().toString()
    }


    fun getConfigDispatchIntervalHours(): Long {
        return config.dispatchIntervalHours
    }

    fun getConfigMaxCachedEvents(): Int {
        return config.maxCachedEvents
    }


}
package com.iamnaran.splinter.core

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.iamnaran.splinter.data.PrefDataStoreManager
import com.iamnaran.splinter.data.model.Event
import com.iamnaran.splinter.data.model.EventStatus
import com.iamnaran.splinter.data.model.Identity
import com.iamnaran.splinter.data.model.Session
import com.iamnaran.splinter.network.DispatcherWorker
import com.iamnaran.splinter.utils.CoroutineDispatcherProvider
import com.iamnaran.splinter.utils.SplinterLog
import com.iamnaran.splinter.utils.Utils.toJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

object SplinterAgent {

    private val TAG = SplinterAgent::class.java.simpleName

    @Volatile
    private var instance: SplinterAgent? = null
    private var contextRef: WeakReference<Context>? = null
    private lateinit var prefDataStoreManager: PrefDataStoreManager
    private lateinit var config: Config
    private var currentSession: Session? = null
    private var identity: Identity? = null

    fun getInstance(context: Context): SplinterAgent {
        return instance ?: synchronized(this) {
            instance ?: SplinterAgent.also {
                it.initalize(context)
                instance = it
            }
        }
    }


    /**
     * Initializes the SplinterAgent with context and config.
     *
     * @param context The application context.
     */
    fun initalize(context: Context) {
        contextRef = WeakReference(context.applicationContext)
        prefDataStoreManager = PrefDataStoreManager.getInstance(context)
        startSession()
    }

    fun configure(config: Config) {
        this.config = config
        if (contextRef?.get() != null) {
            startEventDispatcherWorker(contextRef?.get()!!)

            SplinterLog.info(TAG, "Event Dispatcher Worker Started")

        }
    }


    /**
     * Starts a new session if there is no active session or the current session has timed out.
     */
    private fun startSession() {
        if (contextRef?.get() == null) {
            throw IllegalStateException("SplinterAgent not initialized")
        }
        currentSession =
            if (currentSession?.isActive(config.sessionTimeOutDurationInMinute) == true) {
                currentSession
            } else {
                createSession()
            }

        SplinterLog.info(TAG, "Session has been started")

    }


    /**
     * Creates a new session with a unique ID and current timestamp.
     *
     * @return The new session.
     */
    private fun createSession(): Session {
        return Session()
    }


    /**
     * Logs a new event with optional properties.
     *
     * @param eventName The name of the event.
     * @param properties The properties of the event.
     */
    fun logSplinterEvent(eventName: String, properties: Map<String, Any> = emptyMap()) {
        if (contextRef?.get() == null) {
            throw IllegalStateException("SplinterAgent not initialized")
        }
        startSession()

        val enhancedProperties = properties.toMutableMap().apply {
            put("session_id", currentSession!!.id)
            identity?.let {
                put("user_id", it)
            }
        }

        val newEvent = Event(
            name = eventName,
            properties = enhancedProperties.toJson(),
            eventStatus = EventStatus.CACHED
        )

        CoroutineScope(CoroutineDispatcherProvider.IO).launch {
            prefDataStoreManager.addEventToCache(newEvent)
        }

        SplinterLog.info(TAG, "New Splinter Event Logged")


    }

    private fun startEventDispatcherWorker(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicWorkRequest = PeriodicWorkRequest.Builder(
            DispatcherWorker::class.java,
            config.dispatchIntervalDurationInMinute,
            TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            DispatcherWorker.DISPATCHER_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            periodicWorkRequest
        )

        SplinterLog.info(TAG, "Event dispatcher worker started")
    }


    /**
     * Sets the identity for the current session.
     *
     * @param identity The identity to set.
     */
    fun setIdentity(identity: Identity) {
        this.identity = identity
    }


    /**
     * Gets the active session ID.
     *
     * @return The active session ID.
     */
    fun getActiveSessionId(): String {
        return currentSession?.id ?: throw IllegalStateException("No active session")
    }


    /**
     * Gets the saved events from the preferences.
     *
     * @return The list of saved events.
     */
    public fun getSavedEvents(): Flow<List<Event>> {
        return prefDataStoreManager.getCachedEventListFlow()
    }

}
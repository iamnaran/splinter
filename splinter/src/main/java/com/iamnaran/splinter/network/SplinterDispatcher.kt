package com.iamnaran.splinter.network

import com.iamnaran.splinter.core.Config
import com.iamnaran.splinter.data.PrefDataStoreManager
import com.iamnaran.splinter.data.model.Event
import com.iamnaran.splinter.data.model.EventStatus
import com.iamnaran.splinter.utils.CoroutineDispatcherProvider
import com.iamnaran.splinter.utils.SplinterLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

internal class SplinterDispatcher(
    private val config: Config,
    private val prefDataStoreManager: PrefDataStoreManager,
) {

    private var job: Job? = null
    private var isRunning: Boolean = false

    private val dispatcherScope = CoroutineScope(CoroutineDispatcherProvider.IO + SupervisorJob())

    init {
        startDispatch()
    }

    private fun startDispatch() {
        if (!isRunning) {
            isRunning = true
            job = dispatcherScope.launch {
                while (isActive) {
                    delay(TimeUnit.MINUTES.toMillis(config.dispatchIntervalDurationInMinute))
                    dispatchEvents()
                }
            }
        }
    }

    private suspend fun dispatchEvents() {
        val events = prefDataStoreManager.getCachedEventList()
        SplinterLog.info(TAG, "Dispatching ${events.size} events to server")

        if (events.isNotEmpty()) {
            val httpClient = SplinterHttpClient(config)
            httpClient.sendEvents(events) { success, message ->
                if (success) {
                    dispatcherScope.launch {
                        updateEventStatus(events)
                    }
                    SplinterLog.info(TAG, "Events dispatched successfully")

                } else {
                    SplinterLog.debug(TAG, "Failed to dispatch events: $message")
                }
            }
        }
    }

    private suspend fun updateEventStatus(events: List<Event>) {
        val updatedEvents = events.map { event ->
            event.copy(eventStatus = EventStatus.DELIVERED)
        }
        prefDataStoreManager.saveCachedEventList(updatedEvents)
    }

    fun stopDispatch() {
        job?.cancel()
        isRunning = false
    }

    companion object {
        private const val TAG = "SplinterDispatcher"
    }

}
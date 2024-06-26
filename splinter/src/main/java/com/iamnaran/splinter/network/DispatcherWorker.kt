package com.iamnaran.splinter.network

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.iamnaran.splinter.core.Config
import com.iamnaran.splinter.data.PrefDatastoreManager
import com.iamnaran.splinter.data.model.Event
import com.iamnaran.splinter.data.model.EventStatus
import com.iamnaran.splinter.utils.CoroutineDispatcherProvider
import com.iamnaran.splinter.utils.SplinterLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class DispatcherWorker(
    appContext: Context,
    private val config: Config,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    private val prefDataStoreManager = PrefDatastoreManager.getInstance(appContext)
    private val dispatcherScope = CoroutineScope(CoroutineDispatcherProvider.IO + SupervisorJob())

    override suspend fun doWork(): Result {
        return withContext(CoroutineDispatcherProvider.IO) {
            try {
                dispatchEvents()
                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }

    private suspend fun dispatchEvents() {
        val events = prefDataStoreManager.getCachedEventList()
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

    companion object {
         const val TAG = "SplinterDispatcher"
         const val DISPATCHER_NAME = "X_SPLINTER_DISPATCHER_X"
    }
}
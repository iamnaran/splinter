package com.iamnaran.splinter.meta

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class SplinterWorkerManger(
    appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val eventName = inputData.getString("eventName")
        val params = inputData.keyValueMap.filterKeys { it != "eventName" }

        val success = true

        return if (success) {
            Result.success()
        } else {
            Result.retry()
        }
    }

    private fun sendEventToServer(eventName: String, params: Map<String, String>): Boolean {
        return try {
            println("Sending event '$eventName' with params: $params to server")
            Thread.sleep(2000)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
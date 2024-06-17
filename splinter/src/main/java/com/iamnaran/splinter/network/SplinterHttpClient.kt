package com.iamnaran.splinter.network

import com.iamnaran.splinter.core.Config
import com.iamnaran.splinter.data.model.Event
import com.iamnaran.splinter.utils.Utils.toJson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

internal class SplinterHttpClient(private val config: Config) {

    private val client: OkHttpClient = OkHttpClient.Builder().build()

    fun sendEvents(events: List<Event>, callback: (Boolean, String) -> Unit) {
        val jsonPayload = events.toJson()
        val requestBody = jsonPayload.toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url("serverUrl")
            .post(requestBody)
            .addHeader("Content-Type", "application/json")
            .addHeader("API-KEY", config.splinterAPIKey)
            .addHeader("API-SECRET", config.splinterAPISecret)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(false, e.message ?: "Unknown error")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    callback(true, response.body?.string() ?: "Success")
                } else {
                    callback(false, response.body?.string() ?: "Error")
                }
            }
        })
    }


}
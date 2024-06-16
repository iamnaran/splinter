package com.iamnaran.splinter.network

import com.iamnaran.splinter.core.Config
import java.io.IOException
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

internal class SplinterHttpClient(config: Config) {

    internal fun sendSplinterEventsToServer() {

        // todo implement sending logic
    }

    private fun getConnection(url: String): HttpURLConnection {
        val requestedURL: URL =
            try {
                URL(url)
            } catch (e: MalformedURLException) {
                throw IOException("Invalid url: $url", e)
            }
        val connection = requestedURL.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8")
        connection.setRequestProperty("Accept", "application/json")
        connection.doOutput = true
        connection.doInput = true
        connection.connectTimeout = 15_000
        connection.readTimeout = 20_1000
        return connection
    }


}
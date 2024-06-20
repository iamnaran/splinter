/*
 * Copyright iamnaran (c) 2024. All rights reserved.
 *
 */

package com.iamnaran.splinter

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.work.WorkManager
import com.iamnaran.splinter.core.Config
import com.iamnaran.splinter.core.Splinter
import com.iamnaran.splinter.data.PrefDatastoreManager
import com.iamnaran.splinter.data.model.Event
import com.iamnaran.splinter.data.model.Identity
import com.iamnaran.splinter.utils.CoroutineDispatcherProvider
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.util.prefs.Preferences

class SplinterAgentTest {

    private lateinit var context: Context

    private lateinit var prefDataStoreManager: PrefDatastoreManager
    private lateinit var workManager: WorkManager
    private lateinit var coroutineScope: CoroutineScope
    private lateinit var splinter: Splinter
    @Before
    fun setUp() {
        context = mock(Context::class.java)
        prefDataStoreManager = mock(PrefDatastoreManager::class.java)
        workManager = mock(WorkManager::class.java)
        coroutineScope = CoroutineScope(CoroutineDispatcherProvider.IO)
        splinter = Splinter(
            prefDataStoreManager,
            workManager,
            coroutineScope
        )

    }

    @Test
    fun testInitialize() {
        val config = Config("sadasd","sadsad",sessionTimeOutDurationInMinute = 30, dispatchIntervalDurationInMinute = 15)
        splinter.initialize(config)
        verify(workManager).enqueueUniquePeriodicWork(
            anyString(),
            any(),
            any()
        )
    }


    @Test
    fun testLogSplinterEvent()  {
        val eventName = "test_event"
        val properties = mapOf("key" to "value")

        splinter.logSplinterEvent(eventName, properties)

        coroutineScope.launch {
            verify(prefDataStoreManager).addEventToCache(any())
        }
    }


}
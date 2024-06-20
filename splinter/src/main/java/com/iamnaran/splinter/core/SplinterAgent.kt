/*
 * Copyright iamnaran (c) 2024. All rights reserved.
 *
 */

package com.iamnaran.splinter.core

import android.content.Context
import androidx.work.WorkManager
import com.iamnaran.splinter.data.PrefDatastoreManager
import com.iamnaran.splinter.utils.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineScope

object SplinterAgent {

    @Volatile
    private var instance: Splinter? = null

    fun getInstance(context: Context): Splinter {
        return instance ?: synchronized(this) {
            instance ?: Splinter(
                PrefDatastoreManager.getInstance(context),
                WorkManager.getInstance(context),
                CoroutineScope(CoroutineDispatcherProvider.IO)
            ).also { instance = it }
        }
    }

}
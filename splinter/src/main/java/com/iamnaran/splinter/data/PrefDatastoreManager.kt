package com.iamnaran.splinter.data

import android.content.Context
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.iamnaran.splinter.data.model.Event
import com.iamnaran.splinter.utils.Constants
import com.iamnaran.splinter.utils.Utils.fromJson
import com.iamnaran.splinter.utils.Utils.toJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * Manages preferences data store operations.
 *
 */
open class PrefDatastoreManager(context: Context) : PrefOperations {

    private val dataSource = PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }
        ),
        produceFile = { context.preferencesDataStoreFile(Constants.SPLINTER_PREF_NAME) }
    )


    companion object {
        @Volatile
        private var INSTANCE: PrefDatastoreManager? = null

        fun getInstance(context: Context): PrefDatastoreManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: PrefDatastoreManager(context).also { INSTANCE = it }
            }
        }
    }


    /**
     * Saves a list of cached events to the preferences.
     *
     * @param events List of Event objects to be saved.
     */
    internal suspend fun saveCachedEventList(events: List<Event>) {
        val jsonString = events.toJson()
        saveToPreference(PrefConstants.EVENTS_JSON, jsonString)
    }

    /**
     * Retrieves the cached list of events from preferences.
     *
     * @return List of Event objects retrieved from preferences.
     */
    internal suspend fun getCachedEventList(): List<Event> {
        val jsonString = getFirstPreference(PrefConstants.EVENTS_JSON, "")
        val eventList: List<Event>? = jsonString.fromJson<List<Event>>()
        return eventList ?: emptyList()
    }


    /**
     * Retrieves the cached list of events from preferences.
     *
     * @return List of Event objects retrieved from preferences.
     */
    internal fun getCachedEventListFlow(): Flow<List<Event>> {
        return dataSource.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val jsonString = preferences[PrefConstants.EVENTS_JSON] ?: ""
            val eventList: List<Event>? = jsonString.fromJson<List<Event>>()
            eventList ?: emptyList()
        }
    }


    /**
     * Adds a new event to the cached list and saves it in preferences.
     *
     * @param newEvent The new Event object to be added.
     */

    internal suspend fun addEventToCache(newEvent: Event) {
        val currentEvents = getCachedEventList().toMutableList()
        currentEvents.add(newEvent)
        saveCachedEventList(currentEvents)
    }

    /**
     * Remove event from cached list and saves it in preferences.
     *
     * @param eventsToRemove The new Event object to be added.
     */
    internal suspend fun removeEventsFromCache(eventsToRemove: List<Event>) {
        val currentEvents = getCachedEventList().toMutableList()
        currentEvents.removeAll(eventsToRemove)
        saveCachedEventList(currentEvents)
    }

    override suspend fun <T> getPreferenceFlow(key: Preferences.Key<T>, defaultValue: T):
            Flow<T> = dataSource.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        val result = preferences[key] ?: defaultValue
        result
    }

    override suspend fun <T> getFirstPreference(key: Preferences.Key<T>, defaultValue: T):
            T = dataSource.data.first()[key] ?: defaultValue

    override suspend fun <T> saveToPreference(key: Preferences.Key<T>, value: T) {
        dataSource.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        dataSource.edit { preferences ->
            preferences.remove(key)
        }
    }

    override suspend fun <T> clearAllPreference() {
        dataSource.edit { preferences ->
            preferences.clear()
        }
    }
}


interface PrefOperations {
    suspend fun <T> getPreferenceFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    suspend fun <T> getFirstPreference(key: Preferences.Key<T>, defaultValue: T): T
    suspend fun <T> saveToPreference(key: Preferences.Key<T>, value: T)
    suspend fun <T> removePreference(key: Preferences.Key<T>)
    suspend fun <T> clearAllPreference()
}
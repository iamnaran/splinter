# Splinter

Splinter Analytics is a powerful and lightweight SDK designed to help developers efficiently manage, track, and log events.

## Dependencies
- Kotlin Coroutines
- WorkManager
- Preference DataStore

## Features

- **Event Logging:** 
- **Periodic Batch Log Dispatch:** 
- **Data Persistence:**

## Initialization

Initialize Splinter SDK in your application class or wherever your application context is available:

```kotlin
val splinterConfig = Config("YOUR_API_KEY", "YOUR_API_SECRET").apply {
    dispatchIntervalDurationInMinute = 2L
    maxCachedEvents = 100
    sessionTimeOutDurationInMinute = 1
}
val splinterAgent = SplinterAgent.getInstance(this)
splinterAgent.initialize(splinterConfig)
```

## Logging Events
Log events using Splinter SDK:

```kotlin
SplinterAgent.logSplinterEvent("button_clicked")

val properties = mapOf("button_name" to "submit", "screen" to "login")
SplinterAgent.logSplinterEvent("button_clicked", properties)
```

## Retrieving Events
Retrieve saved events from preferences as a Flow:

``` kotlin
lifecycleScope.launch {
    SplinterAgent.getSavedEvents().collect { events ->
        // Process the list of events
    }
}
```


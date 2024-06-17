# Splinter

Splinter is a lightweight SDK designed to manage and log events efficiently in Android applications.
It provides functionalities to track events, manage sessions, and dispatch events to a server periodically.

## Dependencies

- Kotlin Coroutines: For asynchronous programming.
- WorkManager: For background work scheduling.
- Android DataStore: For data persistence.

## Features

- **Session Management:** Automatically manages session creation and timeout.
- **Event Logging:** Logs events with optional properties.
- **Periodic Event Dispatch:** Uses WorkManager to dispatch cached events at specified intervals.
- **Data Persistence:** Stores events locally using Android's DataStore.

## Initialization

Initialize Splinter SDK in your application class or wherever your application context is available:

```kotlin
class YourApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SplinterAgent.init(this)
    }
}
```

## Configuration
Optionally, you can configure Splinter with a custom Config object for advanced settings:

```kotlin
val config = Config(
    sessionTimeOutDurationInMinute = 30,
    dispatchIntervalDurationInMinute = 15
)
SplinterAgent.setConfiguration(config)
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


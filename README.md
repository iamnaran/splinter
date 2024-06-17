# Splinter

Splinter Analytics is a powerful and lightweight SDK designed to help developers efficiently manage, track, and log events.

## Dependencies
- Kotlin Coroutines
- WorkManager
- Preference DataStore

## Features

- **Event Logging:** 
- **Periodic Event Dispatch:** 
- **Data Persistence:**

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


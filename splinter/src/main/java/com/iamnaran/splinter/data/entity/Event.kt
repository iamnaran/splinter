package com.iamnaran.splinter.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.iamnaran.splinter.data.model.Session

@Entity(
    tableName = "events"
)
data class Event(
    @PrimaryKey(autoGenerate = true) val eventId: Int = 0,

    @SerializedName("session_id")
    @ColumnInfo(name = "session_id") val sessionId: String,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "properties")
    @SerializedName("properties")
    val properties: String,

    @ColumnInfo(name = "status")
    @SerializedName("status")
    val eventStatus: EventStatus,

    @ColumnInfo(name = "createdAt")
    @SerializedName("createdAt")
    val createdAt: String
)


enum class EventStatus {
    CACHED,
    DELIVERED
}





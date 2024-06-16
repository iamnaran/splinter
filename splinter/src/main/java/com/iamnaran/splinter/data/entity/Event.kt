package com.iamnaran.splinter.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "events",
    foreignKeys = [
        ForeignKey(
            entity = Session::class,
            parentColumns = ["sessionId"],
            childColumns = ["sessionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Event(
    @PrimaryKey(autoGenerate = true) val eventId: Int = 0,

    @SerializedName("session_id")
    @ColumnInfo(index = true) val sessionId: Int,

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





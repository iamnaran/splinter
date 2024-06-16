package com.iamnaran.splinter.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("sessions")
data class Session(
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @PrimaryKey val id: String,

    @ColumnInfo(name = "session_id")
    @SerializedName("session_id")
    val sessionId: String,

    @ColumnInfo(name = "startedAt")
    @SerializedName("startedAt")
    val startedAt: Long,

    @ColumnInfo(name = "endAt")
    @SerializedName("endAt")
    val endAt: Long,

    )





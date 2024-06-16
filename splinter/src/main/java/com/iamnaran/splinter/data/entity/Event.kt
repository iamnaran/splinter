package com.iamnaran.splinter.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("user")
data class Event(
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @PrimaryKey val id: String,

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
    val createdAt: String )





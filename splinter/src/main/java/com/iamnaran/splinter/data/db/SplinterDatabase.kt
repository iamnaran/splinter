package com.iamnaran.splinter.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iamnaran.splinter.data.dao.EventDao
import com.iamnaran.splinter.data.dao.SessionDao
import com.iamnaran.splinter.data.entity.Event
import com.iamnaran.splinter.data.entity.Session

@Database(entities = [Session::class, Event::class], version = 3, exportSchema = false)
@TypeConverters(TypeConvertor::class)
abstract class SplinterDatabase : RoomDatabase() {

    abstract fun eventsDao(): EventDao
    abstract fun sessionDao(): SessionDao


}
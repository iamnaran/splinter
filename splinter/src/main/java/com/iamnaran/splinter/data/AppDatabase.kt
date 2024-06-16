package com.iamnaran.splinter.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iamnaran.splinter.data.dao.SpEventDao
import com.iamnaran.splinter.data.entity.SpEvent

@Database(entities = [SpEvent::class], version = 3, exportSchema = false)
@TypeConverters(DbTypeConvertor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun spEventDao(): SpEventDao



}
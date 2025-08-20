package com.chinmay.bloodglucoselogbook.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GlucoseMeasureEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun glucoseMeasureDao(): GlucoseMeasureDao
}
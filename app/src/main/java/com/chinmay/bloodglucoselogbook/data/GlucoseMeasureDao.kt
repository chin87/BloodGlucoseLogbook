package com.chinmay.bloodglucoselogbook.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GlucoseMeasureDao {

    @Query("SELECT * FROM table_glucose_measure")
    fun getAllGlucoseEntries(): List<GlucoseMeasureEntity>

    @Insert
    fun insertGlucoseEntry(vararg glucoseMeasureEntity: GlucoseMeasureEntity)

}

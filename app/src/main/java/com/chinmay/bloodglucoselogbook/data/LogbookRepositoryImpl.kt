package com.chinmay.bloodglucoselogbook.data

import com.chinmay.bloodglucoselogbook.data.model.toGlucoseMeasureDomainModel
import com.chinmay.bloodglucoselogbook.domain.GlucoseMeasurement
import com.chinmay.bloodglucoselogbook.domain.LogbookRepository
import java.util.Collections

class LogbookRepositoryImpl(private val glucoseMeasureDao: GlucoseMeasureDao) : LogbookRepository {
    override suspend fun getGlucoseMeasurements(): List<GlucoseMeasurement> {
        val list =  glucoseMeasureDao.getAllGlucoseEntries().toGlucoseMeasureDomainModel()
        Collections.reverse(list)
        return list
    }

    override suspend fun addGlucoseMeasurement(measurement: GlucoseMeasurement) {
        glucoseMeasureDao.insertGlucoseEntry(
            GlucoseMeasureEntity(
                value = measurement.value,
                unit = measurement.unit,
                time = measurement.time,
                date = measurement.date,
                uid = 0
            )
        )
    }
}
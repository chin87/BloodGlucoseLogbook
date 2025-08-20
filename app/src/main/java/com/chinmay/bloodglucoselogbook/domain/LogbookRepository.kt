package com.chinmay.bloodglucoselogbook.domain

interface LogbookRepository {
    suspend fun getGlucoseMeasurements(): List<GlucoseMeasurement>
    suspend fun addGlucoseMeasurement(measurement: GlucoseMeasurement)
}
package com.chinmay.bloodglucoselogbook.domain

class LogbookUsecase(private val logbookRepository: LogbookRepository) {

    suspend fun getGlucoseMeasurements():List<GlucoseMeasurement> = logbookRepository.getGlucoseMeasurements()

    suspend fun addGlucoseMeasurement(measurement: GlucoseMeasurement) {
        logbookRepository.addGlucoseMeasurement(measurement)
    }
}
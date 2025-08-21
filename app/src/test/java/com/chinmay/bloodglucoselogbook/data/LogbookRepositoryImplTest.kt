package com.chinmay.bloodglucoselogbook.data

import com.chinmay.bloodglucoselogbook.domain.GlucoseMeasurement
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LogbookRepositoryImplTest {

    private lateinit var repository: LogbookRepositoryImpl
    private lateinit var glucoseMeasureDao: GlucoseMeasureDao

    @Before
    fun setup() {
        glucoseMeasureDao = mock()
        repository = LogbookRepositoryImpl(glucoseMeasureDao)
    }

    @Test
    fun `getGlucoseMeasurements returns list of measurements`() = runTest {
        val entities = listOf(
            GlucoseMeasureEntity(1, "100.0", "mmol/L", "12:00", "2022-08-20"),
            GlucoseMeasureEntity(2, "120.0", "mmol/L", "14:00", "2022-08-20")
        )
        val expectedMeasurements = listOf(
            GlucoseMeasurement("120.0", "mmol/L", "14:00", "2022-08-20"),
            GlucoseMeasurement("100.0", "mmol/L", "12:00", "2022-08-20")
        )
        whenever(glucoseMeasureDao.getAllGlucoseEntries()).thenReturn(entities)

        val actualMeasurements = repository.getGlucoseMeasurements()

        assertEquals(expectedMeasurements, actualMeasurements)
    }

    @Test
    fun `addGlucoseMeasurement inserts entry`() = runTest {
        val measurement = GlucoseMeasurement("100.0", "mmol/L", "12:00", "2022-08-20")

        repository.addGlucoseMeasurement(measurement)

        verify(glucoseMeasureDao).insertGlucoseEntry(any())
    }
}

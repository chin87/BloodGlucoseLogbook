package com.chinmay.bloodglucoselogbook.domain

import com.chinmay.bloodglucoselogbook.ui.GlucoseUnit
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LogbookUsecaseTest {

    private lateinit var logbookUsecase: LogbookUsecase
    private lateinit var logbookRepository: LogbookRepository

    @Before
    fun setup() {
        logbookRepository = mock()
        logbookUsecase = LogbookUsecase(logbookRepository)
    }

    @Test
    fun `getGlucoseMeasurements returns list of glucose measurements`() = runTest {
        val expectedMeasurements = listOf(
            GlucoseMeasurement("100.0", GlucoseUnit.MOLE_PER_LITER.toString(), "12:00", "2022-08-20"),
            GlucoseMeasurement("120.0", GlucoseUnit.MOLE_PER_LITER.toString(), "14:00", "2022-08-20")
        )
        whenever(logbookRepository.getGlucoseMeasurements()).thenReturn(expectedMeasurements)

        val actualMeasurements = logbookUsecase.getGlucoseMeasurements()

        assertEquals(expectedMeasurements, actualMeasurements)
    }

    @Test
    fun `addGlucoseMeasurement calls repository`() = runTest {
        val measurement = GlucoseMeasurement("100.0", GlucoseUnit.MOLE_PER_LITER.toString(), "12:00", "2022-08-20")
        logbookUsecase.addGlucoseMeasurement(measurement)
        verify(logbookRepository).addGlucoseMeasurement(measurement)
    }
}

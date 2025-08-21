package com.chinmay.bloodglucoselogbook.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chinmay.bloodglucoselogbook.domain.GlucoseMeasurement
import com.chinmay.bloodglucoselogbook.domain.LogbookUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GlucoseMonitorViewmodel(private val logbookUsecase: LogbookUsecase): ViewModel() {
    var isLoading by mutableStateOf(false)
        protected set
    var measurements by mutableStateOf<List<GlucoseMeasurement>>(emptyList())
        internal set

    var averageValue by mutableStateOf ("")

    init {
        loadMonitorList()
    }

    fun loadMonitorList() {
        isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = logbookUsecase.getGlucoseMeasurements()
            val averageCalculation = setAverageValue(result)
            withContext(Dispatchers.Main) {
                averageValue = averageCalculation
                measurements = result
                isLoading = false
            }
        }
    }

    fun addGlucoseMeasurements(selectedUnit: GlucoseUnit, measurement: Double) {
        viewModelScope.launch(Dispatchers.IO) {

            val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

            val glucoseMeasurement = GlucoseMeasurement(
                measurement.toString(),
                if (selectedUnit == GlucoseUnit.MOLE_PER_LITER) "mmol/L" else "mg/dL",
                timeFormat.format(Date()).toString(),
                dateFormat.format(Date()).toString()
            )
            logbookUsecase.addGlucoseMeasurement(
                glucoseMeasurement
            )
            val result = logbookUsecase.getGlucoseMeasurements()
            val averageCalculation = setAverageValue(result, selectedUnit)
            withContext(Dispatchers.Main) {
                averageValue = averageCalculation
                measurements = result
                isLoading = false
            }
        }
    }

    fun updateAverageValue(selectedUnit: GlucoseUnit){
        averageValue = setAverageValue(measurements, selectedUnit)
    }

    private fun setAverageValue(
        result: List<GlucoseMeasurement>,
        selectedUnit: GlucoseUnit = GlucoseUnit.MOLE_PER_LITER
    ): String {
        var averageCalculation = 0.0
        for (measure in result) {
            when (selectedUnit) {
                GlucoseUnit.MOLE_PER_LITER -> {
                    if (measure.unit.equals("mmol/L")) {
                        averageCalculation = averageCalculation + measure.value.toDouble()
                    } else {
                        averageCalculation =
                            averageCalculation + (measure.value.toDouble() * 0.0555)
                    }
                }

                GlucoseUnit.MG_PER_DECILITER -> {
                    if (measure.unit.equals("mg/dL")) {
                        averageCalculation = averageCalculation + measure.value.toDouble()
                    } else {
                        averageCalculation =
                            averageCalculation + (measure.value.toDouble() / 0.0555)
                    }
                }
            }

        }
        return String.format("%.2f", (averageCalculation / result.size))
    }
}
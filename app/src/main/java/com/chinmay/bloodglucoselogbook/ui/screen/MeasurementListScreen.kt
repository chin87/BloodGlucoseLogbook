@file:OptIn(ExperimentalMaterial3Api::class)
package com.chinmay.bloodglucoselogbook.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chinmay.bloodglucoselogbook.domain.GlucoseMeasurement
import com.chinmay.bloodglucoselogbook.ui.dummyListGlucoseMeasurement

@Composable
fun MeasurementsListScreen( measurements: List<GlucoseMeasurement>, isLoading: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        // Status bar space
        Spacer(modifier = Modifier.height(8.dp))

        // Header
        Text(
            text = "Glucose Measurements",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(
                top = 4.dp,
                bottom = 4.dp)
        )

        Text(
            text = "${measurements.size} total readings",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(
                top = 4.dp,
                bottom = 4.dp)
        )

        // Divider line
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            thickness = 1.dp,
            color = Color.Black
        )

        when {
            isLoading ->
                Box(
                    modifier = Modifier.fillMaxWidth(), // Makes the Box take up the entire available space
                    contentAlignment = Alignment.Center // Centers the content within the Box
                ) {
                    CircularProgressIndicator()
                }
            else ->
                // Measurements list
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(measurements) { measurement ->
                        MeasurementItem(measurement = measurement)
                    }
                }
        }

    }
}

@Composable
fun MeasurementItem(measurement: GlucoseMeasurement) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.Gray.copy(alpha = 0.3f)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "${measurement.value} ${measurement.unit}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Text(
                    text = measurement.date,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Text(
                text = measurement.time,
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true, name = "Measurements List Screen")
@Composable
fun MeasurementsListScreenPreview() {
    MaterialTheme {
        MeasurementsListScreen(dummyListGlucoseMeasurement(), false)
    }
}
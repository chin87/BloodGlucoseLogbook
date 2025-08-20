@file:OptIn(ExperimentalMaterial3Api::class)
package com.chinmay.bloodglucoselogbook.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chinmay.bloodglucoselogbook.ui.GlucoseMonitorViewmodel
import com.chinmay.bloodglucoselogbook.ui.GlucoseUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlucoseTrackerScreen(glucoseMonitorViewmodel: GlucoseMonitorViewmodel) {
    var selectedUnit by remember { mutableStateOf(GlucoseUnit.MOLE_PER_LITER) }
    var glucoseValue by remember { mutableStateOf("0.0") }
    val averageValue = glucoseMonitorViewmodel.averageValue
    val MOLE_PER_LITER = "mmol/L"
    val MG_PER_DECILITER = "mg/dL"

    val isButtonEnabled by remember {
        derivedStateOf {
            try {
                glucoseValue.isNotEmpty() && glucoseValue.toDouble() > 0
            } catch (e: NumberFormatException) {
                false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = 24.dp, start = 6.dp, end = 6.dp, bottom = 2.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Status bar space
        Spacer(modifier = Modifier.height(8.dp))

        // Average display
        Text(
            text = "Your average is $averageValue ${if (selectedUnit == GlucoseUnit.MOLE_PER_LITER) MOLE_PER_LITER else MG_PER_DECILITER}",
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Divider line
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            thickness = 1.dp,
            color = Color.Black
        )

        // Add measurement section
        Text(
            text = "Add measurement:",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Start
        )

        // Radio button group
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup()
                .padding(bottom = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .selectable(
                        selected = (selectedUnit == GlucoseUnit.MG_PER_DECILITER),
                        onClick = {
                            selectedUnit = GlucoseUnit.MG_PER_DECILITER
                            glucoseMonitorViewmodel.updateAverageValue(selectedUnit)
                        }, role = Role.RadioButton
                    )
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (selectedUnit == GlucoseUnit.MG_PER_DECILITER),
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Black
                    )
                )
                Text(
                    text = MG_PER_DECILITER,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .selectable(
                        selected = (selectedUnit == GlucoseUnit.MOLE_PER_LITER),
                        onClick = {
                            selectedUnit = GlucoseUnit.MOLE_PER_LITER
                            glucoseMonitorViewmodel.updateAverageValue(selectedUnit)
                        }, role = Role.RadioButton
                    )
                    .padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (selectedUnit == GlucoseUnit.MOLE_PER_LITER),
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Black
                    )
                )
                Text(
                    text = MOLE_PER_LITER,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        // Input field with unit label
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = glucoseValue,
                onValueChange = { it ->
                    glucoseValue = it
                },
                modifier = Modifier.weight(1f),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                shape = RoundedCornerShape(4.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = if (selectedUnit == GlucoseUnit.MOLE_PER_LITER) MOLE_PER_LITER else MG_PER_DECILITER,
                fontSize = 16.sp,
                color = Color.Black
            )
        }

        // Save button
        Button(
            onClick = {
                println("Saving glucose value: $glucoseValue $selectedUnit")
                glucoseMonitorViewmodel.addGlucoseMeasurements(selectedUnit, glucoseValue.toDouble())
                glucoseValue = ""
            },
            modifier = Modifier
                .width(120.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isButtonEnabled) Color.White else Color.Gray,
                contentColor = if (isButtonEnabled) Color.Black else Color.DarkGray,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.DarkGray
            ),
            shape = RoundedCornerShape(4.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color.Black),
            enabled = isButtonEnabled
        ) {
            Text(
                text = "Save",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GlucoseTrackerScreenPreview() {
    MaterialTheme {
        //GlucoseTrackerScreen()
    }
}
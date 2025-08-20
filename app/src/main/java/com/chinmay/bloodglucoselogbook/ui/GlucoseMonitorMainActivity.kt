package com.chinmay.bloodglucoselogbook.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.chinmay.bloodglucoselogbook.ui.screen.GlucoseTrackerScreen
import com.chinmay.bloodglucoselogbook.ui.screen.MeasurementsListScreen
import com.chinmay.bloodglucoselogbook.ui.theme.BloodGlucoseLogbookTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class GlucoseMonitorMainActivity : ComponentActivity() {
    private val glucoseMonitorViewmodel: GlucoseMonitorViewmodel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BloodGlucoseLogbookTheme {
                Column {
                    GlucoseTrackerScreen(glucoseMonitorViewmodel)
                    MeasurementsListScreen(glucoseMonitorViewmodel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    BloodGlucoseLogbookTheme {
        //GlucoseTrackerScreen()
        //MeasurementsListScreen()
    }
}
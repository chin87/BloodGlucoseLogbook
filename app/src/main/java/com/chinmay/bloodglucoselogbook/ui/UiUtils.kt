package com.chinmay.bloodglucoselogbook.ui

import com.chinmay.bloodglucoselogbook.domain.GlucoseMeasurement

fun dummyListGlucoseMeasurement(): List<GlucoseMeasurement> =
    listOf(
        GlucoseMeasurement("118", "mg/dL", "2:30 PM", "Today"),
        GlucoseMeasurement("125", "mg/dL", "8:15 AM", "Today"),
        GlucoseMeasurement("132", "mg/dL", "9:45 PM", "Yesterday"),
        GlucoseMeasurement("115", "mg/dL", "1:20 PM", "Yesterday"),
        GlucoseMeasurement("128", "mg/dL", "7:30 AM", "Yesterday"),
        GlucoseMeasurement("122", "mg/dL", "6:15 PM", "Aug 17"),
        GlucoseMeasurement("119", "mg/dL", "12:45 PM", "Aug 17"),
        GlucoseMeasurement("134", "mg/dL", "10:30 PM", "Aug 16"),
        GlucoseMeasurement("121", "mg/dL", "3:15 PM", "Aug 16"),
        GlucoseMeasurement("117", "mg/dL", "9:00 AM", "Aug 16"),
        GlucoseMeasurement("126", "mg/dL", "7:45 PM", "Aug 15"),
        GlucoseMeasurement("123", "mg/dL", "11:30 AM", "Aug 15")
    )
package com.chinmay.bloodglucoselogbook.domain

data class GlucoseMeasurement(
    val value: String,
    val unit: String,
    val time: String,
    val date: String
)

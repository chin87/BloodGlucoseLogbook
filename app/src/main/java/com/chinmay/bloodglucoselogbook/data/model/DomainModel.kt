package com.chinmay.bloodglucoselogbook.data.model

import com.chinmay.bloodglucoselogbook.data.GlucoseMeasureEntity
import com.chinmay.bloodglucoselogbook.domain.GlucoseMeasurement

fun List<GlucoseMeasureEntity>.toGlucoseMeasureDomainModel(): List<GlucoseMeasurement> {
    return map { post ->
        GlucoseMeasurement(
            value = post.value ?: "",
            unit = post.unit ?: "",
            time = post.time ?: "",
            date = post.date ?: ""
        )
    }
}
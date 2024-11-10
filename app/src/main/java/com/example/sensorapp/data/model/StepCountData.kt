package com.example.sensorapp.data.model

import com.example.sensorapp.data.remote.CSVExport

data class StepCount(
    val stepCounter: Float = 0f,
    val timestamp: Long? = 0
): CSVExport {
    override fun getCsvBodyRow(): String {
        return "$stepCounter,$timestamp"
    }

    override fun getCsvHeaderRow(): String {
        return "stepCounter,timestamp"
    }

}
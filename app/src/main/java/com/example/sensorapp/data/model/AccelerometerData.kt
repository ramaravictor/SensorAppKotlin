package com.example.sensorapp.data.model

import com.example.sensorapp.data.remote.CSVExport

data class Accelerometer(
    val x: Float = 0f,
    val y: Float = 0f,
    val z: Float = 0f,
    val timestamp: Long? = 0
): CSVExport {
    override fun getCsvBodyRow(): String {
        return "$x,$y,$z,$timestamp"
    }

    override fun getCsvHeaderRow(): String {
        return "x,y,z,timestamp"
    }

}
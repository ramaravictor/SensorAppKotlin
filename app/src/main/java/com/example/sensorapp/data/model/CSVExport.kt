package com.example.sensorapp.data.model

interface CSVExport {
    fun getCsvBodyRow(): String
    fun getCsvHeaderRow(): String
}
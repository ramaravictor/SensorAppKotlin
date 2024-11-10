package com.example.sensorapp.data.remote

interface DataExporter {
    fun getCsvBodyRow(): String
    fun getCsvHeaderRow(): String
}
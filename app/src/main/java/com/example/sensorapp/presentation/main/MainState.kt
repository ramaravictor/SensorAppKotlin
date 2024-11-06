package com.example.sensorapp.presentation.main

data class MainState(
    val light: Float = 0f,
    val accelerometerX: Float = 0f,
    val accelerometerY: Float = 0f,
    val accelerometerZ: Float = 0f,
    val gyroscopeX: Float = 0f,
    val gyroscopeY: Float = 0f,
    val gyroscopeZ: Float = 0f,
    val stepCounter: Float = 0f,
)
package com.example.sensorapp.presentation.debug

data class DebugState(
    val isRecording: Boolean = false,
    val lastStepCounter: Float = 0f,
    val title: String = "Debug Activity Screen"
)
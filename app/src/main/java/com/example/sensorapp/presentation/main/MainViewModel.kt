package com.example.sensorapp.presentation.main

import android.app.Application
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(
    application: Application
) : AndroidViewModel(application), SensorEventListener {
    private val _state = MutableStateFlow(MainState())
    val state = _state
    val context = application
    private val sensorManager: SensorManager =
        getSystemService(context, SensorManager::class.java) as SensorManager

    init {

        val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        accelerometerSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }


        val stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        stepCounterSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_FASTEST)
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        when (p0?.sensor?.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                val x = p0.values[0]
                val y = p0.values[1]
                val z = p0.values[2]
                _state.value = _state.value.copy(
                    accelerometerX = x,
                    accelerometerY = y,
                    accelerometerZ = z
                )
            }

            Sensor.TYPE_STEP_COUNTER -> {
                val steps = p0.values[0]
                _state.value = _state.value.copy(stepCounter = steps)
//                _state.value = _state.value.copy(stepCounter = _state.value.stepCounter + 1)
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onCleared() {
        super.onCleared()
        sensorManager.unregisterListener(this)
    }
}